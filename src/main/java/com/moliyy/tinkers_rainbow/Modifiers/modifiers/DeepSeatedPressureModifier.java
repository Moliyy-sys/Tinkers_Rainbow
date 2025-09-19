package com.moliyy.tinkers_rainbow.Modifiers.modifiers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.tools.Tool;


public class DeepSeatedPressureModifier extends Modifier implements InventoryTickModifierHook {

    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull Level world, @NotNull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, @NotNull ItemStack stack) {
        if (!world.isClientSide && holder.tickCount % 20 == 0 && holder.getUseItem() != stack){
            if (0 < tool.getDamage() && RANDOM.nextFloat() < (modifier.getLevel() * 0.33)){
                int durability = tool.getStats().getInt(ToolStats.DURABILITY);
                if (durability < 200) {
                    ToolDamageUtil.repair(tool, 10);
                }else {
                    if (RANDOM.nextFloat() < 0.33) {
                        ToolDamageUtil.directDamage(tool, 10, holder, stack);
                    }
                }
            }
        }
    }
}
