package com.moliyy.tinkers_rainbow.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModToolTipedRegistry extends Item {
    public final List<Component> tooltip;
    public ModToolTipedRegistry(Item.Properties properties, List<Component> tooltip) {
        super(properties);
        this.tooltip = tooltip;
    }
    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level level, List<net.minecraft.network.chat.Component> list, @NotNull TooltipFlag flag){
        list.addAll(this.tooltip);
        super.appendHoverText(stack, level, list, flag);
    }
}
