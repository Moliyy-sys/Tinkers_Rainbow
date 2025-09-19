package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_BLUE_STEEL_ORE_KEY = registerKey("blue_steel_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_BLUE_STEEL_ORE_KEY = registerKey("nether_blue_steel_ore");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplace = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplace = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldBlockBuleSteelOre = List
                .of(OreConfiguration.target(stoneReplace,ModBlocks.BLUE_STEEL_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplace,ModBlocks.BLUE_STEEL_DEEPSLATE_ORE.get().defaultBlockState()));
        register(context, Feature.ORE,
                new OreConfiguration(overworldBlockBuleSteelOre,9));
    }
    private static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(Tinkers_Rainbow.MOD_ID,name));
    }

    private static <FC extends FeatureConfiguration,F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?,?>> context,
                                                                                         F feature,FC configuration){
        context.register(ModConfiguredFeatures.OVERWORLD_BLUE_STEEL_ORE_KEY, new ConfiguredFeature<>(feature,configuration));
    }
}
