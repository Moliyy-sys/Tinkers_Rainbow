package com.moliyy.tinkers_rainbow.init;

import com.google.common.collect.Sets;
import com.moliyy.tinkers_rainbow.TRMODItems.CosmicNucleusItem;
import com.moliyy.tinkers_rainbow.TRMODItems.EndSingularityItem;
import com.moliyy.tinkers_rainbow.TRMODItems.GloriousCoreItem;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tinkers_Rainbow.MOD_ID);
    public static final LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static RegistryObject<Item> registerItem(String name, Supplier<Item> supplier){
        RegistryObject<Item> item = ITEMS.register(name,supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }
    public static final RegistryObject<Item> BLUE_STEEL = registerItem("blue_steel",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_STEEL_NUGGET = registerItem("blue_steel_nugget",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_JELLY_INGOT = registerItem("green_jelly_ingot",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(5)
                    .effect(()->new MobEffectInstance(MobEffects.HEALTH_BOOST,5*20,2),1)
                    .build())));
    public static final RegistryObject<Item> INDIGO_SCRAP = registerItem("indigo_scrap",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIOLET_CUBE = registerItem("violet_cube",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_NUGGET = registerItem("red_nugget",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_GEM = registerItem("orange_gem",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(3)
                    .effect(()->new MobEffectInstance(MobEffects.LUCK,20*20,2),1)
                    .build())));
    public static final RegistryObject<Item> RAINBOW_INGOT = registerItem("rainbow_ingot",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_DUST = registerItem("yellow_dust",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BLUE_STEEL = registerItem("raw_blue_steel",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> GENE_METAl = registerItem("gene_metal",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> GENE_METAl_NUGGET = registerItem("gene_metal_nugget",
            ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAKAMO_INGOT = registerItem("wakamo_ingot",
            ()->new Item(new Item.Properties()));

    public static final RegistryObject<Item>  GLORIOUS_CORE = registerItem("glorious_core",
            ()->new GloriousCoreItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item>  END_SINGULARITY  = registerItem("end_singularity",
            ()->new EndSingularityItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item>  COSMIC_NUCLEUS  = registerItem("cosmic_nucleus",
            ()->new CosmicNucleusItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC)));




    public static final RegistryObject<Item>  molten_blue_steel_bucket= registerItem("molten_blue_steel_bucket",
           () -> new BucketItem(ModFluid.source_molten_blue_steel, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_indigo_scrap_bucket= registerItem("molten_indigo_scrap_bucket",
            () -> new BucketItem(ModFluid.source_molten_indigo_scrap, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_orange_gem_bucket= registerItem("molten_orange_gem_bucket",
            () -> new BucketItem(ModFluid.source_molten_orange_gem, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_red_nugget_bucket= registerItem("molten_red_nugget_bucket",
            () -> new BucketItem(ModFluid.source_molten_red_nugget, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_violet_cube_bucket= registerItem("molten_violet_cube_bucket",
            () -> new BucketItem(ModFluid.source_molten_violet_cube, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_gene_metal_bucket= registerItem("molten_gene_metal_bucket",
            () -> new BucketItem(ModFluid.source_molten_gene_metal, new Item.Properties().stacksTo(1)));
//    public static final RegistryObject<Item>  molten_rainbow_ingot_bucket= registerItem("molten_rainbow_ingot_bucket",
//            () -> new BucketItem(ModFluid.source_molten_rainbow_ingot, new Item.Properties().stacksTo(1)));



    public static final RegistryObject<Item> RED_SPIDER_SPAWN_EGG= registerItem("red_spider_spawn_egg",
            ()->new ForgeSpawnEggItem(ModEntityTypes.REDSPIDER,0xB22222,0xFF69B4,new Item.Properties()));


    public static final RegistryObject<Item> BLUE_STEEL_BLOCK = registerItem("blue_steel_block",
            ()->new BlockItem(ModBlocks.Blue_Steel_Block.get(),new Item.Properties()));
    public static final RegistryObject<Item> RAINBOW_BLOCK = registerItem("rainbow_block",
            ()->new BlockItem(ModBlocks.Rainbow_Block.get(),new Item.Properties()));
    public static final RegistryObject<Item> GREEN_JELLY_BLOCK = registerItem("green_jelly_block",
            ()->new BlockItem(ModBlocks.Green_Jelly_Block.get(),new Item.Properties()));

    public static final RegistryObject<Item> BLUE_STEEL_ORE= registerItem("blue_steel_ore",
            ()->new BlockItem(ModBlocks.BLUE_STEEL_ORE.get(),new Item.Properties()));
    public static final RegistryObject<Item> BLUE_STEEL_DEEPSLATE_ORE = registerItem("blue_steel_deepslate_ore",
            ()->new BlockItem(ModBlocks.BLUE_STEEL_DEEPSLATE_ORE.get(),new Item.Properties()));

    public static final RegistryObject<Item> GENE_FURNACE = registerItem("gene_furnace",
            () -> new BlockItem(ModBlocks.Gene_Furnace_Block.get(), new Item.Properties()));

}
