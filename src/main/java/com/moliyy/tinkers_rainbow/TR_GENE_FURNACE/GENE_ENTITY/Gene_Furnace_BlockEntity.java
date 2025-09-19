package com.moliyy.tinkers_rainbow.TR_GENE_FURNACE.GENE_ENTITY;

import com.moliyy.tinkers_rainbow.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Gene_Furnace_BlockEntity extends BlockEntity{
    public Gene_Furnace_BlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GENE_FURNACE_ENTITY.get(),pos, state);
    }

}
