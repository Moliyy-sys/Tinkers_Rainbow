package com.moliyy.tinkers_rainbow.init;

import com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe.GeneOvenRecipe;
import com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe.GeneOvenRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, "tinkers_rainbow");

    public static final RegistryObject<RecipeSerializer<GeneOvenRecipe>> GENE_OVEN_SERIALIZER =
            SERIALIZERS.register("gene_oven", GeneOvenRecipeSerializer::new);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
