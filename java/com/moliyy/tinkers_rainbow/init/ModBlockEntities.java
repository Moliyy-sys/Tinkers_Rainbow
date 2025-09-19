package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TR_GENE_FURNACE.GENE_BLOCK.Gene_Furnace_Block;
import com.moliyy.tinkers_rainbow.TR_GENE_FURNACE.GENE_ENTITY.Gene_Furnace_BlockEntity;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Tinkers_Rainbow.MOD_ID);

    public static final RegistryObject<BlockEntityType<?>> GENE_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("gene_furnace_entity", () ->
                    BlockEntityType.Builder.of(Gene_Furnace_BlockEntity::new,
                            ModBlocks.Gene_Furnace_Block.get()).build(null));
}
