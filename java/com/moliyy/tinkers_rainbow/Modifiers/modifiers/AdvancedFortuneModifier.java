package com.moliyy.tinkers_rainbow.Modifiers.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.EnchantmentModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockHarvestModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;


public class AdvancedFortuneModifier extends Modifier implements BlockHarvestModifierHook, EnchantmentModifierHook.SingleHarvestEnchantment {

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookbuilder){
        super.registerHooks(hookbuilder);
        hookbuilder.addHook(this, ModifierHooks.BLOCK_HARVEST);
        hookbuilder.addHook(this,ModifierHooks.ENCHANTMENTS);
    }
    private static final List<ExtraDrop> EXTRA_DROPS = List.of(
            new ExtraDrop(Items.COAL, 0.17),
            new ExtraDrop(Items.RAW_COPPER, 0.15),
            new ExtraDrop(Items.RAW_IRON, 0.13),
            new ExtraDrop(Items.RAW_GOLD, 0.11),
            new ExtraDrop(Items.REDSTONE,0.10),
            new ExtraDrop(Items.QUARTZ, 0.09),
            new ExtraDrop(Items.AMETHYST_SHARD, 0.09),
            new ExtraDrop(Items.LAPIS_LAZULI, 0.07),
            new ExtraDrop(Items.DIAMOND, 0.05),
            new ExtraDrop(Items.EMERALD, 0.03),
            new ExtraDrop(Items.NETHERITE_SCRAP, 0.01)
    );

    @Override
    public void finishHarvest(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolHarvestContext context, int harvested) {

        if (context.getWorld().isClientSide()) return;

        int level = modifier.getLevel();
        BlockState state = context.getState();
        BlockPos pos = context.getPos();

        if (!isTargetOre(state)) return;

        processExtraDrops(context, level, pos);
    }
    private void processExtraDrops(ToolHarvestContext context, int level, BlockPos pos) {
        for (ExtraDrop drop : EXTRA_DROPS) {

            double adjustedChance = drop.baseChance * (1 + 0.15 * level);
            if (context.getWorld().getRandom().nextDouble() < adjustedChance) {
                ItemEntity itemEntity = new ItemEntity(
                        context.getWorld(),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        new ItemStack(drop.item)
                );
                context.getWorld().addFreshEntity(itemEntity);
            }
        }
    }
    @Override
    public int getEnchantmentLevel(@NotNull IToolStackView tool, ModifierEntry modifier) {
        return modifier.getLevel() + 1;
    }

    private boolean isTargetOre(BlockState state) {
        return state.is(Tags.Blocks.ORES) ||
                state.getBlock() == Blocks.ANCIENT_DEBRIS;
    }

    @Override
    public @NotNull Enchantment getEnchantment(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier) {
        return Enchantments.BLOCK_FORTUNE;
    }
        private record ExtraDrop(Item item, double baseChance) {
    }
}