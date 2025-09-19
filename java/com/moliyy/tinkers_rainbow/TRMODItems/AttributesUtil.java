package com.moliyy.tinkers_rainbow.TRMODItems;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class AttributesUtil {

    /**
     * 更新实体属性修饰符
     * @param attribute 要修改的属性
     * @param modifierId 修饰符ID
     * @param entity 目标实体
     * @param amount 要增加的值
     */
    public static void updateAttrModifierBy(Attribute attribute, UUID modifierId, LivingEntity entity, double amount) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            // 移除现有的修饰符
            attributeInstance.removeModifier(modifierId);

            // 创建新的修饰符
            AttributeModifier modifier = new AttributeModifier(
                    modifierId,
                    "Tinkers Rainbow Modifier",
                    amount,
                    AttributeModifier.Operation.ADDITION
            );

            // 添加修饰符
            attributeInstance.addPermanentModifier(modifier);
        }
    }

    /**
     * 获取实体属性修饰符的值
     * @param attribute 属性
     * @param modifierId 修饰符ID
     * @param entity 目标实体
     * @return 修饰符的值，如果不存在则返回0
     */
    public static double getAttrModifierValue(Attribute attribute, UUID modifierId, LivingEntity entity) {
        AttributeInstance attributeInstance = entity.getAttribute(attribute);
        if (attributeInstance != null) {
            AttributeModifier modifier = attributeInstance.getModifier(modifierId);
            if (modifier != null) {
                return modifier.getAmount();
            }
        }
        return 0;
    }
}