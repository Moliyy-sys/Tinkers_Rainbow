package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRmodeffects.DamageAccumulationCooldownEffect;
import com.moliyy.tinkers_rainbow.TRmodeffects.DamageAccumulationEffect;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TRModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Tinkers_Rainbow.MOD_ID);

    public static final RegistryObject<MobEffect> ACCUMULATION_EFFECT = EFFECTS.register("accumulation", DamageAccumulationEffect::new);

    public static final RegistryObject<MobEffect> ACCUMULATION_COOLDOWN = EFFECTS.register("accumulation_cool", DamageAccumulationCooldownEffect::new);

}
