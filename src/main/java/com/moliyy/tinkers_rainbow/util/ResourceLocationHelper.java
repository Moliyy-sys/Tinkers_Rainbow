package com.moliyy.tinkers_rainbow.util;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation resource(String path){
        return location(Tinkers_Rainbow.MOD_ID,path);
    }
    public static ResourceLocation location(String modid,String name){
        return ResourceLocation.fromNamespaceAndPath(modid,name);
    }
}
