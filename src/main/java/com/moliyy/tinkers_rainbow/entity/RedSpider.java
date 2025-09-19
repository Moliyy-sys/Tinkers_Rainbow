package com.moliyy.tinkers_rainbow.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedSpider extends Spider {
    public RedSpider(EntityType<? extends Spider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static boolean camRedSpiderSpawn(EntityType<? extends Monster> redSpiderEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return serverLevelAccessor.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(redSpiderEntityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource);
    }
    public static AttributeSupplier.Builder registerAttributes() {
        return Spider.createAttributes()
                .add(Attributes.MAX_HEALTH,40.0f)
                .add(Attributes.ARMOR,10.0f)
                .add(Attributes.ARMOR_TOUGHNESS,5.0f)
                .add(Attributes.ATTACK_DAMAGE,4.0f);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entity) {
        if (super.doHurtTarget(entity)){
            if (entity instanceof LivingEntity livingEntity){
                livingEntity.setSecondsOnFire(5*20);
            }
            return true;
        }else {
            return false;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag pDataTag) {
        return spawnGroupData;
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pDimensions) {
        return 0.40f;
    }


}
