package co.eltrut.differentiate.datagen;

import co.eltrut.differentiate.datagen.common.RecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class DifferentiateDataGenerator implements DataGeneratorEntrypoint {
    private RecipeGenerator recipes;

    public void addRecipe(Consumer<Consumer<FinishedRecipe>> recipe) {
        this.recipes.addRecipe(recipe);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        this.recipes = new RecipeGenerator(gen);
        gen.addProvider(this.recipes);
    }
}