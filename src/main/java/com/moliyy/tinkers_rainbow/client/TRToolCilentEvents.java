package com.moliyy.tinkers_rainbow.client;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.mantle.data.listener.ISafeManagerReloadListener;
import slimeknights.tconstruct.common.ClientEventBase;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.client.model.DynamicTextureLoader;
//import slimeknights.tconstruct.library.client.model.TinkerItemProperties;
import slimeknights.tconstruct.library.client.modifiers.ModifierModelManager;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Tinkers_Rainbow.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class TRToolCilentEvents extends ClientEventBase {
    private static final ISafeManagerReloadListener MODIFIER_RELOAD_LISTENER = manager-> ModifierManager.INSTANCE.getAllValues().forEach(modifier -> modifier.clearCache(PackType.CLIENT_RESOURCES));
    public TRToolCilentEvents(){

    }
    @SubscribeEvent
    static void addResourceListener(RegisterClientReloadListenersEvent manager){
        ModifierModelManager.init(manager);
        MaterialTooltipCache.init(manager);
        DynamicTextureLoader.init(manager);
        manager.registerReloadListener(MODIFIER_RELOAD_LISTENER);
    }
//    @SubscribeEvent
//    static void cilentSetup(FMLClientSetupEvent event) {
//        event.enqueueWork(() -> {
//            TinkerItemProperties.registerToolProperties(ModItems.paxel.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.knife.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.mace.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.arrow_thrower.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.magma_staff.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.quartz_staff.get().asItem());
//            TinkerItemProperties.registerToolProperties(ModItems.clay_staff.get().asItem());
//        });
//    }
}
