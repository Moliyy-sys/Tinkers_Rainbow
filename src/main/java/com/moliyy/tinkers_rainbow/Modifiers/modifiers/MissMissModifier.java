package com.moliyy.tinkers_rainbow.Modifiers.modifiers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MissMissModifier extends Modifier implements DamageBlockModifierHook {
    private static final float BASE_DODGE_CHANCE = 0.2f;
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookbuilder){
        super.registerHooks(hookbuilder);
        hookbuilder.addHook(this, ModifierHooks.DAMAGE_BLOCK);
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        if (context.getEntity().level().isClientSide) return false;


        if (context.getEntity().getRandom().nextFloat() < BASE_DODGE_CHANCE) {

            spawnDodgeEffects(context.getEntity());
            return true;
        }
        return false;
    }


    private void spawnDodgeEffects(LivingEntity entity) {
        Level level = entity.level();


        for(int i = 0; i < 8; ++i) {
            level.addParticle(ParticleTypes.CLOUD,
                    entity.getX() + (level.random.nextDouble() - 0.5) * entity.getBbWidth(),
                    entity.getY() + level.random.nextDouble() * entity.getBbHeight(),
                    entity.getZ() + (level.random.nextDouble() - 0.5) * entity.getBbWidth(),
                    0, 0.1, 0);
        }


        level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS,
                0.8F, 1.5F + level.random.nextFloat() * 0.4F);
    }
}
