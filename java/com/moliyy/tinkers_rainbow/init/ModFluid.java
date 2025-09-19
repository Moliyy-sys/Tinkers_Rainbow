package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluid {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Tinkers_Rainbow.MOD_ID);
    public static final RegistryObject<FlowingFluid> source_molten_blue_steel = FLUIDS.register("molten_blue_steel",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_blue_steel));
    public static final RegistryObject<FlowingFluid> flowing_molten_blue_steel = FLUIDS.register("flowing_molten_blue_steel",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_blue_steel));
    public static final ForgeFlowingFluid.Properties molten_blue_steel = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_blue_steel, source_molten_blue_steel, flowing_molten_blue_steel).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_blue_steel_block).bucket(ModItems.molten_blue_steel_bucket);
    public static final RegistryObject<FlowingFluid> source_molten_indigo_scrap = FLUIDS.register("molten_indigo_scrap",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_indigo_scrap));
    public static final RegistryObject<FlowingFluid> flowing_molten_indigo_scrap = FLUIDS.register("flowing_molten_indigo_scrap",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_indigo_scrap));
    public static final ForgeFlowingFluid.Properties molten_indigo_scrap = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_indigo_scrap, source_molten_indigo_scrap, flowing_molten_indigo_scrap).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_indigo_scrap_block).bucket(ModItems.molten_indigo_scrap_bucket);
    public static final RegistryObject<FlowingFluid> source_molten_orange_gem = FLUIDS.register("molten_orange_gem",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_orange_gem));
    public static final RegistryObject<FlowingFluid> flowing_molten_orange_gem = FLUIDS.register("flowing_molten_orange_gem",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_orange_gem));
    public static final ForgeFlowingFluid.Properties molten_orange_gem = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_orange_gem, source_molten_orange_gem, flowing_molten_orange_gem).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_orange_gem_block).bucket(ModItems.molten_orange_gem_bucket);
    public static final RegistryObject<FlowingFluid> source_molten_violet_cube = FLUIDS.register("molten_violet_cube",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_violet_cube));
    public static final RegistryObject<FlowingFluid> flowing_molten_violet_cube = FLUIDS.register("flowing_molten_violet_cube",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_violet_cube));
    public static final ForgeFlowingFluid.Properties molten_violet_cube = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_violet_cube, source_molten_violet_cube, flowing_molten_violet_cube).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_violet_cube_block).bucket(ModItems.molten_violet_cube_bucket);
    public static final RegistryObject<FlowingFluid> source_molten_red_nugget = FLUIDS.register("molten_red_nugget",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_red_nugget));
    public static final RegistryObject<FlowingFluid> flowing_molten_red_nugget = FLUIDS.register("flowing_molten_red_nugget",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_red_nugget));
    public static final ForgeFlowingFluid.Properties molten_red_nugget = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_red_nugget, source_molten_red_nugget, flowing_molten_red_nugget).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_red_nugget_block).bucket(ModItems.molten_red_nugget_bucket);
    public static final RegistryObject<FlowingFluid> source_molten_gene_metal = FLUIDS.register("molten_gene_metal",
            () -> new ForgeFlowingFluid.Source(ModFluid.molten_gene_metal));
    public static final RegistryObject<FlowingFluid> flowing_molten_gene_metal = FLUIDS.register("flowing_molten_gene_metal",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.molten_gene_metal));
    public static final ForgeFlowingFluid.Properties molten_gene_metal = new ForgeFlowingFluid.Properties(
            ModFluidTypes.molten_gene_metal, source_molten_gene_metal, flowing_molten_gene_metal).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(ModBlocks.molten_gene_metal_block).bucket(ModItems.molten_gene_metal_bucket);

    public static void registers(IEventBus eventBus)  {
        FLUIDS.register(eventBus);
    }
    }
//    public static final RegistryObject<FlowingFluid> source_molten_rainbow_ingot = FLUIDS.register("molten_rainbow_ingot",
//            ()->new ForgeFlowingFluid.Source(ModFluid.molten_rainbow_ingot));
//    public static final RegistryObject<FlowingFluid> flowing_molten_rainbow_ingot = FLUIDS.register("flowing_molten_rainbow_ingot",
//            ()->new ForgeFlowingFluid.Flowing(ModFluid.molten_rainbow_ingot));
//    public static final ForgeFlowingFluid.Properties molten_rainbow_ingot = new ForgeFlowingFluid.Properties(
//            ModFluidTypes.molten_rainbow_ingot, source_molten_rainbow_ingot,flowing_molten_rainbow_ingot).slopeFindDistance(2).levelDecreasePerBlock(2)
//            .block(ModBlocks.molten_rainbow_ingot_block).bucket(ModItems.molten_rainbow_ingot_bucket);

