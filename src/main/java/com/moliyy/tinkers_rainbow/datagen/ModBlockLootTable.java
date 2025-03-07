package com.moliyy.tinkers_rainbow.datagen;

import com.moliyy.tinkers_rainbow.registry.ModBlocks;
import com.moliyy.tinkers_rainbow.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTable extends BlockLootSubProvider {
    protected ModBlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.Blue_Steel_Block.get());
        dropSelf(ModBlocks.Rainbow_Block.get());
        dropSelf(ModBlocks.Green_Jelly_Block.get());
        dropSelf(ModBlocks.molten_blue_steel_block.get());
        dropSelf(ModBlocks.molten_indigo_scrap_block.get());
        dropSelf(ModBlocks.molten_orange_gem_block.get());
        dropSelf(ModBlocks.molten_red_nugget_block.get());
        dropSelf(ModBlocks.molten_violet_cube_block.get());
        dropSelf(ModBlocks.molten_black_opal_block.get());

        //dropOther(ModBlocks.BLUE_STEEL_ORE.get(),ModItems.RAW_BLUE_STEEL.get());
        //dropOther(ModBlocks.BLUE_STEEL_DEEPSLATE_ORE.get(),ModItems.RAW_BLUE_STEEL.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
