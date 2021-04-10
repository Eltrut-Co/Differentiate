package co.eltrut.differentiate.core.datagen.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;

public class DifferRecipeProvider extends RecipeProvider {

	private List<Consumer<Consumer<IFinishedRecipe>>> recipes;
	
	public DifferRecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
		this.recipes = new ArrayList<>();
	}
	
	public void addRecipe(Consumer<Consumer<IFinishedRecipe>> recipe) {
		this.recipes.add(recipe);
	}
	
	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		for (Consumer<Consumer<IFinishedRecipe>> recipe : this.recipes) {
			recipe.accept(consumer);
		}
	}

}
