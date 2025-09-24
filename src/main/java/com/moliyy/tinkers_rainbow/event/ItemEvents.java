package com.moliyy.tinkers_rainbow.event;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = "tinkers_rainbow", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemEvents {

    // 辉煌核心的修饰符UUID
    public static final UUID GLORY_CORE_HEALTH_ID = UUID.fromString("1e5a3c7d-9f82-4a3b-9e21-6b8a4d2c1f7e");

    // 终焉奇点的修饰符UUID
    public static final UUID END_SINGULARITY_HEALTH_ID = UUID.fromString("2e6a4d8e-0a93-4b1c-8e25-7c9b5d3e2f8f");

    // 寰宇晶核的修饰符UUID
    public static final UUID COSMIC_NUCLEUS_HEALTH_ID = UUID.fromString("3f7b5e9f-1b84-4c2d-9f36-8d0a6e4f3a0c");
    public static final UUID COSMIC_NUCLEUS_ATTACK_ID = UUID.fromString("4a8c6f0a-2c95-4d3e-0a47-9e1b7f5d4e2f");

    @SubscribeEvent
    public static void onPlayerCloneDeath(PlayerEvent.Clone event) {
        // 检查是否是死亡复活（而不是从末地返回）
        if (event.isWasDeath()) {
            Player original = event.getOriginal();
            Player newPlayer = event.getEntity();

            // 恢复辉煌核心的使用次数和属性
            restoreItemUsesAndAttributes(original, newPlayer, "TinkersRainbow_GloryCoreUses", GLORY_CORE_HEALTH_ID);

            // 恢复终焉奇点的使用次数和属性
            restoreItemUsesAndAttributes(original, newPlayer, "TinkersRainbow_EndSingularityUses", END_SINGULARITY_HEALTH_ID);

            // 恢复寰宇晶核的使用次数和属性
            restoreItemUsesAndAttributes(original, newPlayer, "TinkersRainbow_CosmicNucleusUses", COSMIC_NUCLEUS_HEALTH_ID);
            restoreItemUsesAndAttributes(original, newPlayer, "TinkersRainbow_CosmicNucleusUses", COSMIC_NUCLEUS_ATTACK_ID);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();

        // 确保玩家复活后生命值不超过最大值
        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    // 辅助方法：恢复物品使用次数和属性修饰符
    private static void restoreItemUsesAndAttributes(Player original, Player newPlayer, String usesKey, UUID modifierId) {
        // 恢复使用次数
        int uses = original.getPersistentData().getInt(usesKey);
        newPlayer.getPersistentData().putInt(usesKey, uses);

        // 恢复属性修饰符
        AttributeInstance originalAttribute = original.getAttribute(Attributes.MAX_HEALTH);
        if (originalAttribute != null) {
            AttributeModifier modifier = originalAttribute.getModifier(modifierId);
            if (modifier != null) {
                newPlayer.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier);
            }
        }

        // 对于寰宇晶核的攻击力修饰符，需要特殊处理
        if (modifierId.equals(COSMIC_NUCLEUS_ATTACK_ID)) {
            AttributeInstance originalAttack = original.getAttribute(Attributes.ATTACK_DAMAGE);
            if (originalAttack != null) {
                AttributeModifier attackModifier = originalAttack.getModifier(modifierId);
                if (attackModifier != null) {
                    newPlayer.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(attackModifier);
                }
            }
        }
    }
}
