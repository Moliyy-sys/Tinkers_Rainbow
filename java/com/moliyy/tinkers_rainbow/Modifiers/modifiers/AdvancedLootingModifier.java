package com.moliyy.tinkers_rainbow.Modifiers.modifiers;

import com.moliyy.tinkers_rainbow.init.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.EnchantmentModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.LootingModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.LootingContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class AdvancedLootingModifier extends Modifier implements LootingModifierHook, ProcessLootModifierHook, EnchantmentModifierHook.SingleEnchantment {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookbuilder){
        super.registerHooks(hookbuilder);
        hookbuilder.addHook(this, ModifierHooks.WEAPON_LOOTING);
        hookbuilder.addHook(this,ModifierHooks.ENCHANTMENTS);
        hookbuilder.addHook(this,ModifierHooks.PROCESS_LOOT);
    }
    private static final List<RegistryObject<Item>> EXTRA_LOOT = List.of(
            ModItems.GENE_METAl_NUGGET // 可扩展其他物品
    );
    private static final float BASE_DROP_CHANCE = 0.25f;
    @Override
    public int getEnchantmentLevel(IToolStackView tool, ModifierEntry modifier) {
        return modifier.getLevel() + 1;
    }
    @Override
    public int updateLooting(IToolStackView tool, ModifierEntry modifier, LootingContext context, int looting) {
        return looting + modifier.getLevel();
    }

    @Override
    public Enchantment getEnchantment(IToolStackView tool, ModifierEntry modifier) {
        return Enchantments.MOB_LOOTING;
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        Entity targetEntity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (!(targetEntity instanceof LivingEntity target)) return;

        // 获取持有者（假设为攻击者）
        LivingEntity holder = context.getParamOrNull(LootContextParams.LAST_DAMAGE_PLAYER);
        if (holder == null) return;

        // 正确构建LootingContext
        DamageSource damageSource = context.getParamOrNull(LootContextParams.DAMAGE_SOURCE);
        LootingContext lootingContext = new LootingContext(
                holder,       // 攻击者
                target,       // 被攻击的生物
                damageSource, // 伤害来源
                EquipmentSlot.MAINHAND // 假设使用主手槽位
        );

        // 获取有效抢夺等级
        int effectiveLevel = LootingModifierHook.getLooting(tool, lootingContext, 0);

        // 使用LootContext的随机数
        RandomSource random = context.getRandom();

        addExtraLoot(generatedLoot, modifier.getLevel(), effectiveLevel, random);
    }
    private void addExtraLoot(List<ItemStack> loot, int modifierLevel,
                              int lootingLevel, RandomSource random) {
        float chance = BASE_DROP_CHANCE * (1 + 0.15f * modifierLevel);
        int count = 1 + random.nextInt(lootingLevel + 1);

        if (random.nextFloat() < chance && !EXTRA_LOOT.isEmpty()) {
            // 修复1：从列表中随机选择索引
            int index = random.nextInt(EXTRA_LOOT.size());
            // 修复2：获取实际的Item对象
            Item extraItem = EXTRA_LOOT.get(index).get();
            loot.add(new ItemStack(extraItem, count));
        }
    }
}
