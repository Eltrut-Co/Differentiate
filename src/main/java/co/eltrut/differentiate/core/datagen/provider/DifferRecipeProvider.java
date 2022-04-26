package co.eltrut.differentiate.core.datagen.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class DifferRecipeProvider extends RecipeProvider {
	private List<Consumer<Consumer<FinishedRecipe>>> recipes;
	
	public DifferRecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
		this.recipes = new ArrayList<>();
	}
	
	public void addRecipe(Consumer<Consumer<FinishedRecipe>> recipe) {
		this.recipes.add(recipe);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		for (Consumer<Consumer<FinishedRecipe>> recipe : this.recipes) {
			recipe.accept(consumer);
		}
	}
}