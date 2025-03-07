package com.moliyy.tinkers_rainbow.reference;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.resources.ResourceLocation;

public class Reference {
    public static String tab(){
        return String.format("itemGroup.%s", Tinkers_Rainbow.MOD_ID);
    }
    public static ResourceLocation location(String pathIn) {
        return new ResourceLocation(pathIn);
    }
    public static ResourceLocation forge(String path) {
        return new ResourceLocation("forge", path);
    }
    public static ResourceLocation raw_ores(String path) {
        return forge("raw_ores/" + path);
    }
    public static ResourceLocation material(String path) {
        return forge("raw_materials/" + path);
    }
    public static ResourceLocation ingot(String path) {
        return forge("ingots/" + path);
    }
    public static ResourceLocation dust(String path) {
        return forge("dusts/" + path);
    }
    public static ResourceLocation nugget(String path) {
        return forge("nuggets/" + path);
    }
    public static ResourceLocation ore(String path) {
        return forge("ores/" + path);
    }
    public static ResourceLocation block(String path) {
        return forge("storage_blocks/" + path);
    }
    public static ResourceLocation raw_block(String path) {
        return forge("raw_blocks/" + path);
    }
}
