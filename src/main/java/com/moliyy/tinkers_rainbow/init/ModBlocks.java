package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.block.Gene_Oven_Block;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.fluids.block.BurningLiquidBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tinkers_Rainbow.MOD_ID);
    private static final BlockBehaviour.Properties CommonLiquid = BlockBehaviour.Properties.copy(Blocks.LAVA);

    public static final RegistryObject<Block> Blue_Steel_Block = BLOCKS.register("blue_steel_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> Green_Jelly_Block = BLOCKS.register("green_jelly_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));
    public static final RegistryObject<Block> Rainbow_Block = BLOCKS.register("rainbow_block",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> Gene_Oven = BLOCKS.register("gene_oven",
            ()->new Gene_Oven_Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK )));

    public static final RegistryObject<Block> BLUE_STEEL_ORE = BLOCKS.register("blue_steel_ore",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> BLUE_STEEL_DEEPSLATE_ORE = BLOCKS.register("blue_steel_deepslate_ore",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<LiquidBlock> molten_blue_steel_block = BLOCKS.register("molten_blue_steel_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_blue_steel,CommonLiquid,10,2f));
    public static final RegistryObject<LiquidBlock> molten_indigo_scrap_block = BLOCKS.register("molten_indigo_scrap_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_indigo_scrap,CommonLiquid,10,2f));
    public static final RegistryObject<LiquidBlock> molten_orange_gem_block = BLOCKS.register("molten_orange_gem_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_orange_gem,CommonLiquid,10,2f));
    public static final RegistryObject<LiquidBlock> molten_red_nugget_block = BLOCKS.register("molten_red_nugget_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_red_nugget,CommonLiquid,10,2f));
    public static final RegistryObject<LiquidBlock> molten_violet_cube_block = BLOCKS.register("molten_violet_cube_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_violet_cube,CommonLiquid,10,2f));
    public static final RegistryObject<LiquidBlock> molten_gene_metal_block = BLOCKS.register("molten_gene_metal_block",
            ()->new BurningLiquidBlock(ModFluid.source_molten_gene_metal,CommonLiquid,30,6f));
//    public static final RegistryObject<LiquidBlock> molten_rainbow_ingot_block = BLOCKS.register("molten_rainbow_ingot_block",
//            ()->new BurningLiquidBlock(ModFluid.source_molten_rainbow_ingot,CommonLiquid,9999,99f));






}
