package com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe;

import com.moliyy.tinkers_rainbow.init.ModRecipeSerializers;
import com.moliyy.tinkers_rainbow.init.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class GeneOvenRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final int cookingTime;

    public GeneOvenRecipe(ResourceLocation id, NonNullList<Ingredient> inputs,
                          ItemStack output, int cookingTime) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.cookingTime = cookingTime;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        for (int i = 0; i < inputs.size(); i++) {
            if (!inputs.get(i).test(inv.getItem(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(Container inv, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.GENE_OVEN_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.GENE_OVEN_TYPE.get();
    }

    public NonNullList<Ingredient> getInputs() {
        return inputs;
    }

    public int getCookingTime() {
        return cookingTime;
    }
}
