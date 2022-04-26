package co.eltrut.differentiate.core.datagen;

import java.util.function.Consumer;

import co.eltrut.differentiate.core.datagen.provider.DifferRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Generator {
	private final DataGenerator generator;
	private final ExistingFileHelper helper;
	
	private final DifferRecipeProvider recipes;

	public Generator(DataGenerator generator, ExistingFileHelper helper, String modid) {
		this.generator = generator;
		this.helper = helper;
		
		this.recipes = new DifferRecipeProvider(generator);
	}
	
	public void addRecipe(Consumer<Consumer<FinishedRecipe>> recipe) {
		this.recipes.addRecipe(recipe);
	}
	
	public void run() {
		this.generator.addProvider(this.recipes);
	}
}