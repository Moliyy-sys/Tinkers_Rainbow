package com.moliyy.tinkers_rainbow.datagen;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
<<<<<<< HEAD
import com.moliyy.tinkers_rainbow.init.ModConfiguredFeatures;
import com.moliyy.tinkers_rainbow.init.ModPlacedFeatures;
=======
import com.moliyy.tinkers_rainbow.registry.ModConfiguredFeatures;
import com.moliyy.tinkers_rainbow.registry.ModPlacedFeatures;
>>>>>>> 981271775e69e87b2613d06676af9d23474e788f
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootscrap);
    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER, Set.of(Tinkers_Rainbow.MOD_ID));
    }
}
