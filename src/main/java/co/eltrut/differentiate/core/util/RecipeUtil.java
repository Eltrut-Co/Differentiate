package co.eltrut.differentiate.core.util;

import java.util.Arrays;
import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.CuttingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.DifferShapedRecipeBuilder;
import co.eltrut.differentiate.core.datagen.DifferShapelessRecipeBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeUtil {
	
	public static Ingredient[] toIngredients(Item ...items) {
		return Arrays.stream(items).map(s -> Ingredient.fromStacks(new ItemStack(s))).toArray(Ingredient[]::new);
	}
	
	public static DifferShapedRecipeBuilder shapedRecipe(IItemProvider resultIn) {
		return shapedRecipe(resultIn, 1);
	}

	public static DifferShapedRecipeBuilder shapedRecipe(IItemProvider resultIn, int countIn) {
		return new DifferShapedRecipeBuilder(resultIn, countIn);
	}
	
	public static DifferShapelessRecipeBuilder shapelessRecipe(IItemProvider resultIn) {
		return shapelessRecipe(resultIn, 1);
	}

	public static DifferShapelessRecipeBuilder shapelessRecipe(IItemProvider resultIn, int countIn) {
		return new DifferShapelessRecipeBuilder(resultIn, countIn);
	}
	
	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient[] ingredientIn, IItemProvider resultIn) {
		return stonecuttingRecipe(ingredientIn, resultIn, 1);
	}

	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient[] ingredientIn, IItemProvider resultIn, int countIn) {
		return new CuttingRecipeBuilder(IRecipeSerializer.STONECUTTING, ingredientIn, resultIn, countIn);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient[] ingredientIn, IItemProvider resultIn) {
		return sawingRecipe(ingredientIn, resultIn, 1);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient[] ingredientIn, IItemProvider resultIn, int countIn) {
		return new CuttingRecipeBuilder(ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation("environmental", "sawing")), ingredientIn, resultIn, countIn);
	}
	
	public static void slabCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void stairsCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(result, 4)
    		.patternLine("X  ")
    		.patternLine("XX ")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void wallCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void verticalSlabCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block verticalSlab, Block slab, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(verticalSlab, 3)
    		.patternLine("X")
    		.patternLine("X")
    		.patternLine("X")
    		.key('X', slab)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
    		.build(consumer);
	}
	
	public static void verticalSlabRevertCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block slab, Block verticalSlab, String[] mods, String[] conditions, String[] flags) {
		shapelessRecipe(slab)
			.addIngredient(verticalSlab.asItem())
			.addModCompat(mods)
			.addConditions(conditions)
			.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
			.build(consumer);
	}

}
