package co.eltrut.differentiate.core.datagen.helper;

import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.builder.DifferShapedRecipeBuilder;
import co.eltrut.differentiate.core.datagen.builder.DifferShapelessRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;

public class CraftingHelper {

	public static void createSlab(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void createStairs(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 4)
    		.patternLine("X  ")
    		.patternLine("XX ")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void createWall(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void createVerticalSlab(Consumer<IFinishedRecipe> consumer, Block verticalSlab, Block slab, String[] mods, String[] conditions, String[] flags) {
		DifferShapedRecipeBuilder.shapedRecipe(verticalSlab, 3)
    		.patternLine("X")
    		.patternLine("X")
    		.patternLine("X")
    		.key('X', slab)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
    		.build(consumer);
	}
	
	public static void createVerticalSlabRevert(Consumer<IFinishedRecipe> consumer, Block slab, Block verticalSlab, String[] mods, String[] conditions, String[] flags) {
		DifferShapelessRecipeBuilder.shapelessRecipe(slab)
			.addIngredient(verticalSlab.asItem())
			.addModCompat(mods)
			.addConditions(conditions)
			.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
			.build(consumer);
	}
	
	
}
