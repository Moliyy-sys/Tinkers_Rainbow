package com.moliyy.tinkers_rainbow.TRMODItems;

import com.moliyy.tinkers_rainbow.Config;
import com.moliyy.tinkers_rainbow.event.ItemEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EndSingularityItem extends Item {
    // 冷却时间（ticks）
    private static final int COOLDOWN = 10;

    public EndSingularityItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // 检查冷却时间
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.pass(stack);
        }

        if (!level.isClientSide) {
            // 获取配置的最大使用数量
            int maxUses = Config.MAX_END_SINGULARITY_USES.get();

            // 检查玩家当前的使用次数
            int currentUses = player.getPersistentData().getInt("TinkersRainbow_EndSingularityUses");

            if (currentUses < maxUses) {
                // 增加使用次数
                player.getPersistentData().putInt("TinkersRainbow_EndSingularityUses", currentUses + 1);

                // 增加最大生命值（每使用一次增加3颗心，即6点生命值）
                increaseMaxHealth(player, 6.0);

                // 消耗物品（非创造模式）
                if (!player.isCreative()) {
                    stack.shrink(1);
                }

                // 设置冷却时间
                player.getCooldowns().addCooldown(this, COOLDOWN);

                // 播放使用音效
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        net.minecraft.sounds.SoundEvents.ENDER_DRAGON_AMBIENT,
                        net.minecraft.sounds.SoundSource.PLAYERS,
                        0.7F, 1.0F);

                // 发送反馈消息
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.translatable("message.tinkers_rainbow.end_singularity_used",
                                currentUses + 1, maxUses),
                        true
                );

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            } else {
                // 已达到最大使用次数
                player.displayClientMessage(
                        net.minecraft.network.chat.Component.translatable("message.tinkers_rainbow.end_singularity_max"),
                        true
                );

                return InteractionResultHolder.fail(stack);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    // 增加最大生命值的方法
    private void increaseMaxHealth(Player player, double amount) {
        AttributeInstance healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (healthAttribute != null) {
            // 获取当前的使用次数
            int currentUses = player.getPersistentData().getInt("TinkersRainbow_EndSingularityUses");

            // 移除现有的修饰符
            healthAttribute.removeModifier(ItemEvents.END_SINGULARITY_HEALTH_ID);

            // 创建新的修饰符
            AttributeModifier healthModifier = new AttributeModifier(
                    ItemEvents.END_SINGULARITY_HEALTH_ID,
                    "End Singularity Health Bonus",
                    currentUses * amount, // 总加成 = 使用次数 × 每次加成
                    AttributeModifier.Operation.ADDITION
            );

            // 添加修饰符
            healthAttribute.addPermanentModifier(healthModifier);

            // 确保玩家当前生命值不超过新的最大值
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }
    }

    // 获取玩家的终焉奇点使用次数（静态方法，可从其他地方调用）
    public static int getEndSingularityUses(Player player) {
        return player.getPersistentData().getInt("TinkersRainbow_EndSingularityUses");
    }

    // 设置玩家的终焉奇点使用次数（静态方法，可从其他地方调用）
    public static void setEndSingularityUses(Player player, int uses) {
        player.getPersistentData().putInt("TinkersRainbow_EndSingularityUses", uses);
    }
}