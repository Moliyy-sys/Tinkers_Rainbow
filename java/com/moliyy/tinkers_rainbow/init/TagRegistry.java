package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.reference.Reference;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
    public static final TagKey<Block> BLUE_STEEL_ORE = BlockTags.create(Reference.ore("blue_steel"));
    public static final TagKey<Item> RAW_BLUE_STEEL = ItemTags.create(Reference.material("blue_steel"));
}
