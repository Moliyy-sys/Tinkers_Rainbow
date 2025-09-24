package com.moliyy.tinkers_rainbow.TRmodeffects;

import com.moliyy.tinkers_rainbow.init.TRModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class DamageAccumulationEffect extends MobEffect {

    // 定义持久化数据的键名
    private static final String ACCUMULATED_KEY = "damage_accumulation_accumulated";
    private static final String MAX_ACCUMULATED_KEY = "damage_accumulation_max";

    public DamageAccumulationEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // 深红色效果粒子
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // 空实现 - 实际逻辑在事件处理器中
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 每tick都检查
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        private static final int TRIGGER_TICKS = 10 * 20; // 10秒（20 ticks/秒）

        @SubscribeEvent
        public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
            LivingEntity entity = event.getEntity();

            // 确保实体有我们的效果
            if (!entity.hasEffect(TRModEffects.ACCUMULATION_EFFECT.get())) return;

            // 获取效果实例
            MobEffectInstance effect = entity.getEffect(TRModEffects.ACCUMULATION_EFFECT.get());

            // 获取持久化数据
            CompoundTag persistentData = entity.getPersistentData();

            // 检查是否包含累积伤害键
            if (!persistentData.contains(ACCUMULATED_KEY)) return;

            float accumulated = persistentData.getFloat(ACCUMULATED_KEY);

            // 检查持续时间结束（10秒）
            if (effect.getDuration() <= 1) { // 下个tick将结束
                System.out.println("时间结束触发效果! 累积伤害: " + accumulated);
                triggerEffect(entity, accumulated);
                entity.removeEffect(TRModEffects.ACCUMULATION_EFFECT.get());
            }
        }

        private static void triggerEffect(LivingEntity target, float damage) {
            // 使用魔法伤害类型作为"真实伤害"
            target.hurt(target.damageSources().magic(), damage);
            System.out.println("造成真实伤害: " + damage);

            // 清除持久化数据
            CompoundTag persistentData = target.getPersistentData();
            persistentData.remove(ACCUMULATED_KEY);
            persistentData.remove(MAX_ACCUMULATED_KEY);

            // 添加冷却效果（20秒）
            target.addEffect(new MobEffectInstance(
                    TRModEffects.ACCUMULATION_COOLDOWN.get(),
                    20 * 20 // 20秒 * 20 ticks/秒
            ));
        }
    }
}