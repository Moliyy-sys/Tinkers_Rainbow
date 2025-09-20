package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.Modifiers.modifiers.*;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TRModModifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(Tinkers_Rainbow.MOD_ID);

    public static final StaticModifier<Modifier> DeepSeatedPressure = MODIFIERS.register("deep_seated_pressure", DeepSeatedPressureModifier::new);
    public static final StaticModifier<Modifier> AdvancedFortune = MODIFIERS.register("advanced_fortune", AdvancedFortuneModifier::new);
    public static final StaticModifier<Modifier> AdvancedLooting = MODIFIERS.register("advanced_looting", AdvancedLootingModifier::new);
    public static final StaticModifier<Modifier> MissMiss = MODIFIERS.register("miss_miss", MissMissModifier::new);
    public static final StaticModifier<Modifier> CrimsonBloom = MODIFIERS.register("crimsonbloom", CrimsonBloomModifier::new);

}
