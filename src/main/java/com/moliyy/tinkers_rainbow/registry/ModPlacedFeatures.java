package com.moliyy.tinkers_rainbow.registry;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BULE_STEEL_ORE_KEY = createKey();

    public static void bootscrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, configured.getOrThrow(ModConfiguredFeatures.OVERWORLD_BLUE_STEEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64),VerticalAnchor.absolute(80))));
    }
    private static ResourceKey<PlacedFeature> createKey(){
        return ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(Tinkers_Rainbow.MOD_ID, "blue_steel_ore_placed"));
    }
    private static void register(BootstapContext<PlacedFeature> context,
                                 Holder<ConfiguredFeature<?,?>> configured, List<PlacementModifier> modifiers){
        context.register(ModPlacedFeatures.BULE_STEEL_ORE_KEY, new PlacedFeature(configured,List.copyOf(modifiers)));
    }

    static class ModOrePlacement{
        private static List<PlacementModifier> orePlacement(PlacementModifier modifier,PlacementModifier modifier1){
            return List.of(modifier, InSquarePlacement.spread(),modifier1, BiomeFilter.biome());
        }
        private static List<PlacementModifier> commonOrePlacement(PlacementModifier p_195345_){
            return orePlacement(CountPlacement.of(16),p_195345_);
        }
        private static List<PlacementModifier> rareOrePlaceMent(int p_195350_,PlacementModifier p_195351_){
            return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_),p_195351_);
        }
    }
}
