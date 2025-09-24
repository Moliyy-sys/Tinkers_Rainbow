package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.menu.GeneOvenMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, "tinkers_rainbow");

    public static final RegistryObject<MenuType<GeneOvenMenu>> GENE_OVEN_MENU =
            MENU_TYPES.register("gene_oven", () ->
                    new MenuType<>(GeneOvenMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus bus) {
        MENU_TYPES.register(bus);
    }
}
