package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe.GeneOvenRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, "tinkers_rainbow");

    public static final RegistryObject<RecipeType<GeneOvenRecipe>> GENE_OVEN_TYPE =
            RECIPE_TYPES.register("gene_oven", () ->
                    RecipeType.simple(new ResourceLocation("tinkers_rainbow", "gene_oven"))
            );

    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
    }
}
