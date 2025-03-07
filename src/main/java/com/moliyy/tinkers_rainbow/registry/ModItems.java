package com.moliyy.tinkers_rainbow.registry;

import com.google.common.collect.Sets;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.tinkering.Tinkers_RainbowToolDefinitions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableBowItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

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





    public static final RegistryObject<Item>  molten_blue_steel_bucket= registerItem("molten_blue_steel_bucket",
           () -> new BucketItem(ModFluid.source_molten_blue_steel, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_indigo_scrap_bucket= registerItem("molten_indigo_scrap_bucket",
            () -> new BucketItem(ModFluid.source_molten_indigo_scrap, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_orange_gem_bucket= registerItem("molten_orange_gem_bucket",
            () -> new BucketItem(ModFluid.source_molten_orange_gem, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_red_nugget_bucket= registerItem("molten_red_nugget_bucket",
            () -> new BucketItem(ModFluid.source_molten_red_nugget, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>  molten_black_opal_bucket= registerItem("molten_black_opal_bucket",
            () -> new BucketItem(ModFluid.source_molten_black_opal, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>  molten_violet_cube_bucket= registerItem("molten_violet_cube_bucket",
            () -> new BucketItem(ModFluid.source_molten_violet_cube, new Item.Properties().stacksTo(1)));
//    public static final RegistryObject<Item> molten_rainbow_ingot_bucket = registerItem("molten_rainbow_ingot_bucket",
//            ()->new Item(new Item.Properties().stacksTo(1)));





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


    public static final RegistryObject<ModifiableItem> paxel = ITEMS.register("paxel",()->new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.PAXEL));
    public static final RegistryObject<ModifiableItem> knife = ITEMS.register("knife",()->new ModifiableItem(new Item.Properties().stacksTo(1),Tinkers_RainbowToolDefinitions.KNIFE));
    public static final RegistryObject<ModifiableItem>  mace = ITEMS.register( "mace", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.MACE));
    public static final RegistryObject<ModifiableBowItem> arrow_thrower = ITEMS.register("arrow_thrower", () -> new ModifiableBowItem(new Item.Properties().stacksTo(1),  Tinkers_RainbowToolDefinitions.ARROW_THROWER));
    public static final RegistryObject<ModifiableItem> magma_staff = ITEMS.register("magma_staff", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.MAGMA_STAFF));
    public static final RegistryObject<ModifiableItem> clay_staff = ITEMS.register("clay_staff", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.CLAY_STAFF));
    public static final RegistryObject<ModifiableItem> quartz_staff = ITEMS.register("quartz_staff", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.QUARTZ_STAFF));
    public static final RegistryObject<ModifiableItem> seared_bucket = ITEMS.register("seared_bucket", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.SEARED_BUCKET));
    public static final RegistryObject<ModifiableItem> tinkers_bronze_bucket = ITEMS.register("tinkers_bronze_bucket", () -> new ModifiableItem(new Item.Properties().stacksTo(1), Tinkers_RainbowToolDefinitions.TINKERS_BRONZE_BUCKET));
    public static final RegistryObject<ModifiableItem> battle_bucket = ITEMS.register("battle_bucket", () -> new ModifiableItem(new Item.Properties().stacksTo(1).fireResistant(), Tinkers_RainbowToolDefinitions.BATTLE_BUCKET));
    public static final RegistryObject<ToolPartItem> narrow_blade = ITEMS.register("narrow_blade", () -> new ToolPartItem(new Item.Properties(), HeadMaterialStats.ID));

}
