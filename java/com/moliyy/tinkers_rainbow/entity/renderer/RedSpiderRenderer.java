package com.moliyy.tinkers_rainbow.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.entity.RedSpider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RedSpiderRenderer extends SpiderRenderer<RedSpider> {
    private static final ResourceLocation RED_ZOMBIE_TEXTURES = new ResourceLocation(Tinkers_Rainbow.MOD_ID,"textures/entity/spider/redspider.png");
    public RedSpiderRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
        this.shadowRadius *= 0.6F;
    }

    @Override
    protected void scale(@NotNull RedSpider pLivingEntity, PoseStack stack, float pPartialTickTime) {
        stack.scale(0.6F,0.6F,0.6F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RedSpider pEntity) {
        return RED_ZOMBIE_TEXTURES;
    }
}
