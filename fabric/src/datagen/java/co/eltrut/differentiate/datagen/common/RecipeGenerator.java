package co.eltrut.differentiate.datagen.common;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    private final List<Consumer<Consumer<FinishedRecipe>>> recipes;

    public RecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Consumer<Consumer<FinishedRecipe>> recipe) {
        this.recipes.add(recipe);
    }

    @Override
    protected void generateRecipes(Consumer<FinishedRecipe> exporter) {
        for (Consumer<Consumer<FinishedRecipe>> recipe : this.recipes) {
            recipe.accept(exporter);
        }
    }
}