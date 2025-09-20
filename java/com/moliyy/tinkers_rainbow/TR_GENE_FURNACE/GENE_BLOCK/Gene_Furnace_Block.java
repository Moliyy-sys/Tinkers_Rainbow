package com.moliyy.tinkers_rainbow.TR_GENE_FURNACE.GENE_BLOCK;

import com.moliyy.tinkers_rainbow.TR_GENE_FURNACE.GENE_ENTITY.Gene_Furnace_BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Gene_Furnace_Block extends BaseEntityBlock {
    public Gene_Furnace_Block(Properties properties){
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new Gene_Furnace_BlockEntity(pos,state);
    }
}
