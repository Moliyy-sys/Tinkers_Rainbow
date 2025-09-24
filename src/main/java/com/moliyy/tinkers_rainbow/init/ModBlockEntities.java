package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.block.GeneOvenBlockEntity;
import com.moliyy.tinkers_rainbow.TRNewBlockEntity.block.Gene_Oven_Block;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "tinkers_rainbow");

    public static final RegistryObject<BlockEntityType<GeneOvenBlockEntity>> GENE_OVEN =
            BLOCK_ENTITIES.register("gene_oven", () ->
                    BlockEntityType.Builder.of(
                            GeneOvenBlockEntity::new,
                            ModBlocks.Gene_Oven.get()
                    ).build(null)
            );
}
