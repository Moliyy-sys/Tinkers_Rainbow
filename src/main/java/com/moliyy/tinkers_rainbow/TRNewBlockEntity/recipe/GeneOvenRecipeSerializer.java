package com.moliyy.tinkers_rainbow.TRNewBlockEntity.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class GeneOvenRecipeSerializer implements RecipeSerializer<GeneOvenRecipe> {
    @Override
    public GeneOvenRecipe fromJson(ResourceLocation id, JsonObject json) {
        // 解析输入
        JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
        NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

        for (int i = 0; i < Math.min(ingredients.size(), 4); i++) {
            inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
        }

        // 解析输出
        ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

        // 解析烹饪时间
        int cookingTime = GsonHelper.getAsInt(json, "cookingtime", 200);

        return new GeneOvenRecipe(id, inputs, output, cookingTime);
    }

    @Override
    public GeneOvenRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

        for (int i = 0; i < 4; i++) {
            inputs.set(i, Ingredient.fromNetwork(buf));
        }

        ItemStack output = buf.readItem();
        int cookingTime = buf.readVarInt();

        return new GeneOvenRecipe(id, inputs, output, cookingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, GeneOvenRecipe recipe) {
        for (Ingredient ingredient : recipe.getInputs()) {
            ingredient.toNetwork(buf);
        }

        buf.writeItem(recipe.getResultItem(null));
        buf.writeVarInt(recipe.getCookingTime());
    }
}
