package com.moliyy.tinkers_rainbow.datagen;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Tinkers_Rainbow.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true,new ModItemModelProvider(packOutput,helper));
        generator.addProvider(true,new ModBlockStateProvider(packOutput,helper));
        generator.addProvider(true,ModLootTableProvider.create(packOutput));
        generator.addProvider(true,new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(),new ModWorldGenProvider(packOutput,lookupProvider));
    }
}
