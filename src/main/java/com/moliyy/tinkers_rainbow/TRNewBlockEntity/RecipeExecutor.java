package com.moliyy.tinkers_rainbow.TRNewBlockEntity;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecipeExecutor<T extends Recipe<Container>> {
    private final Supplier<T> recipeSupplier;
    private final Consumer<T> onComplete;
    private final int maxProgress;

    public RecipeExecutor(Supplier<T> recipeSupplier, Consumer<T> onComplete, int maxProgress) {
        this.recipeSupplier = recipeSupplier;
        this.onComplete = onComplete;
        this.maxProgress = maxProgress;
    }

    public boolean hasRecipe() {
        return recipeSupplier.get() != null;
    }

    public void execute() {
        T recipe = recipeSupplier.get();
        if (recipe != null) {
            onComplete.accept(recipe);
        }
    }

    public int getMaxProgress() {
        return maxProgress;
    }
}
