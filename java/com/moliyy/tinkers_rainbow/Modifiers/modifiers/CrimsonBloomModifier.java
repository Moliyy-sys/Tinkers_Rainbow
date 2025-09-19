package com.moliyy.tinkers_rainbow.Modifiers.modifiers;
//TRModEffects.ACCUMULATION_EFFECT.get()
import com.moliyy.tinkers_rainbow.init.TRModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;


public class CrimsonBloomModifier extends Modifier implements MeleeHitModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookbuilder){
        super.registerHooks(hookbuilder);
        hookbuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    private static final String ACCUMULATED_KEY = "damage_accumulation_accumulated";
    private static final String MAX_ACCUMULATED_KEY = "damage_accumulation_max";

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier,
                              ToolAttackContext context, float damageDealt) {

        // 获取攻击目标（被攻击的实体）
        LivingEntity target = context.getLivingTarget();
        if (target == null) {
            System.out.println("攻击目标为空");
            return;
        }

        // 获取攻击者（使用工具的角色）
        LivingEntity attacker = context.getAttacker();
        if (!(attacker instanceof Player)) {
            System.out.println("攻击者不是玩家");
            return;
        }

        // 检查冷却状态
        if (target.hasEffect(TRModEffects.ACCUMULATION_COOLDOWN.get())) {
            System.out.println("目标处于冷却状态");
            return;
        }

        // 获取修饰符等级
        int level = modifier.getLevel();

        // 计算最大积蓄值：1000 + 500 * level
        float maxAccumulation = 1000 + 500 * level;

        // 获取目标的持久化数据
        CompoundTag persistentData = target.getPersistentData();

        // 获取当前累积伤害
        float accumulated = persistentData.contains(ACCUMULATED_KEY) ?
                persistentData.getFloat(ACCUMULATED_KEY) : 0;

        // 更新累积伤害（不超过上限）
        accumulated += damageDealt;
        accumulated = Math.min(accumulated, maxAccumulation);

        // 存储更新后的值
        persistentData.putFloat(ACCUMULATED_KEY, accumulated);
        persistentData.putFloat(MAX_ACCUMULATED_KEY, maxAccumulation);

        System.out.println("累积伤害: " + accumulated + "/" + maxAccumulation + " (等级: " + level + ")");

        // 检查触发条件（生命值≤积蓄伤害）
        if (target.getHealth() <= accumulated) {
            System.out.println("触发效果! 生命值: " + target.getHealth() + " <= 累积伤害: " + accumulated);
            triggerEffect(target, accumulated);
            target.removeEffect(TRModEffects.ACCUMULATION_EFFECT.get());
            return; // 效果已触发，不需要应用/更新效果
        }

        // 应用/更新效果
        if (!target.hasEffect(TRModEffects.ACCUMULATION_EFFECT.get())) {
            System.out.println("应用新效果: 累积伤害=" + accumulated + ", 上限=" + maxAccumulation);

            // 创建新效果实例（10秒 = 200 ticks）
            MobEffectInstance newEffect = new MobEffectInstance(
                    TRModEffects.ACCUMULATION_EFFECT.get(),
                    200, // 10秒 * 20 ticks/秒
                    0,
                    false,
                    true
            );

            target.addEffect(newEffect);
        } else {
            // 如果已有效果，只更新持续时间
            MobEffectInstance existingEffect = target.getEffect(TRModEffects.ACCUMULATION_EFFECT.get());
            if (existingEffect != null) {
                System.out.println("更新效果持续时间");

                MobEffectInstance updatedEffect = new MobEffectInstance(
                        existingEffect.getEffect(),
                        200, // 重置为10秒
                        existingEffect.getAmplifier(),
                        existingEffect.isAmbient(),
                        existingEffect.isVisible(),
                        existingEffect.showIcon()
                );
                target.addEffect(updatedEffect);
            }
        }
    }

    private void triggerEffect(LivingEntity target, float damage) {
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
