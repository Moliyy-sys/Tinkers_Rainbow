package com.moliyy.tinkers_rainbow.init;

import com.mojang.serialization.Codec;
import com.moliyy.tinkers_rainbow.Loot.WitherDropGloryCoreModifier;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM_CODECS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Tinkers_Rainbow.MOD_ID);

    // 注册全局战利品修改器
    public static final RegistryObject<Codec<WitherDropGloryCoreModifier>> WITHER_DROP_GLORY_CORE =
            GLM_CODECS.register("wither_drop_glories_core", WitherDropGloryCoreModifier.CODEC);
}
