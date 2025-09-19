package com.moliyy.tinkers_rainbow.datagen;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Tinkers_Rainbow.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.Blue_Steel_Block);
        blockWithItem(ModBlocks.Rainbow_Block);
        blockWithItem(ModBlocks.Green_Jelly_Block);
        blockWithItem(ModBlocks.BLUE_STEEL_ORE);
        blockWithItem(ModBlocks.BLUE_STEEL_DEEPSLATE_ORE);

        simpleBlock(ModBlocks.Gene_Furnace_Block.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gene_furnace")));
    }
    public void blockWithItem(RegistryObject<Block> block){
        simpleBlockWithItem(block.get(),cubeAll(block.get()));
    }
}
