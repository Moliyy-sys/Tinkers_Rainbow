package com.moliyy.tinkers_rainbow.datagen;

import com.moliyy.tinkers_rainbow.Tinkers_Rainbow;
import com.moliyy.tinkers_rainbow.init.ModItems;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        smeltingRecipe(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.YELLOW_DUST.get())
                .pattern("asa")
                .pattern("sps")
                .pattern("asa")
                .define('s',ModItems.GREEN_JELLY_INGOT.get())
                .define('p', Items.APPLE)
                .define('a',Items.BLAZE_POWDER)
                .unlockedBy("has_yellow_dust",hasItem(ModItems.YELLOW_DUST.get()))
                .save(consumer);

    }
    private CriterionTriggerInstance hasItem(ItemLike item){
        return inventoryTrigger(ItemPredicate.Builder.item().of(item).build());
    }
    private void smeltingRecipe(Consumer<FinishedRecipe> consumer){
        smelting(ModItems.RAW_BLUE_STEEL.get(),RecipeCategory.MISC,ModItems.BLUE_STEEL.get(),0.5f,2000).
                unlockedBy("has_blue_steel",hasItem(ModItems.BLUE_STEEL.get()))
                .save(consumer,new ResourceLocation(Tinkers_Rainbow.MOD_ID,"blue_steel").toString()
                 + "_from_smelting");
    }
    private SimpleCookingRecipeBuilder smelting(ItemLike item,RecipeCategory category,ItemLike result,float xp,int time){
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(item),
                category,result,xp,time);

    }
    private SimpleCookingRecipeBuilder blasting(ItemLike item,RecipeCategory category,ItemLike result,float xp,int time){
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(item),
                        category,result,xp,time)
                .unlockedBy("has_blue_steel",hasItem(ModItems.BLUE_STEEL.get()));
    }
}
