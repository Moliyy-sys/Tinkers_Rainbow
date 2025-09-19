package com.moliyy.tinkers_rainbow;


import com.moliyy.tinkers_rainbow.TRModMoreFluid.Rainbow_Ingot_Fluid;
import com.moliyy.tinkers_rainbow.TRModMoreFluid.wakamo_Ingot_Fluid;
import com.moliyy.tinkers_rainbow.entity.renderer.RedSpiderRenderer;
import com.moliyy.tinkers_rainbow.init.*;
//import net.minecraft.client.gui.screens.MenuScreens;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Tinkers_Rainbow.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Tinkers_Rainbow {
    public static final String MOD_ID = "tinkers_rainbow";
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("removal")
    public Tinkers_Rainbow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPE.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModFluid.registers(modEventBus);
        ModFluidTypes.registers(modEventBus);
        TRModModifiers.MODIFIERS.register(modEventBus);
        TRModEffects.EFFECTS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModLootModifier.GLM_CODECS.register(modEventBus);
        //ModMenuTypes.register(modEventBus);
        //ModRecipes.register(modEventBus);

        Rainbow_Ingot_Fluid.FLUIDS.register(modEventBus);
        wakamo_Ingot_Fluid.FLUIDS.register(modEventBus);


        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerRenderers);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == ModCreativeModeTabs.TINKERS_RAINBOW_TAB.get()){
            ModItems.CREATIVE_TAB_ITEMS.forEach(event::accept);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.RED_SPIDER_SPAWN_EGG);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Tinker's Rainbow common setup complete");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Tinker's Rainbow server starting");
    }
    public static String makeDescriptionId(String type,String name){
        return type + "." + MOD_ID + "." + name;
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntityTypes.REDSPIDER.get(), RedSpiderRenderer::new);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus =  Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
//        @SubscribeEvent
//        public static void onClientSetup(FMLClientSetupEvent event){
//            MenuScreens.register(ModMenuTypes.GENE_FURNACE_MENU.get(), Gene_Furnace_Screen::new);
//
//        }
    }
}
