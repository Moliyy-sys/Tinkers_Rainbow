package com.moliyy.tinkers_rainbow.TRmodeffects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DamageAccumulationCooldownEffect extends MobEffect{
    public DamageAccumulationCooldownEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFFFFFF); // 透明效果
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // 空实现 - 仅用于标记冷却状态
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false; // 不需要每tick执行
    }
}
