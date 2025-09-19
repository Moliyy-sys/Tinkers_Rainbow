package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Tinkers_Rainbow.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            Tinkers_Rainbow.MOD_ID);
    public static final RegistryObject<CreativeModeTab> TINKERS_RAINBOW_TAB = CREATIVE_MODE_TABS.register("tinkers_rainbow_tab",
            ()->CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RAINBOW_INGOT.get()))
                    .title(Component.translatable("itemGroup.tinkers_rainbow_tab"))
                    .build());
}
