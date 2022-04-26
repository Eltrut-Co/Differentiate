package co.eltrut.differentiate.core.util;

import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.builder.CuttingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.builder.DifferCookingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.builder.DifferShapedRecipeBuilder;
import co.eltrut.differentiate.core.datagen.builder.DifferShapelessRecipeBuilder;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeUtil {
	public static DifferShapedRecipeBuilder shapedRecipe(ItemLike resultIn) {
		return shapedRecipe(resultIn, 1);
	}

	public static DifferShapedRecipeBuilder shapedRecipe(ItemLike resultIn, int countIn) {
		return new DifferShapedRecipeBuilder(resultIn, countIn);
	}
	
	public static DifferShapelessRecipeBuilder shapelessRecipe(ItemLike resultIn) {
		return shapelessRecipe(resultIn, 1);
	}

	public static DifferShapelessRecipeBuilder shapelessRecipe(ItemLike resultIn, int countIn) {
		return new DifferShapelessRecipeBuilder(resultIn, countIn);
	}
	
	public static DifferCookingRecipeBuilder cookingRecipe(Ingredient ingredientIn, ItemLike resultIn,
			float experienceIn, int cookingTimeIn, SimpleCookingSerializer<?> serializer) {
		return new DifferCookingRecipeBuilder(resultIn, ingredientIn, experienceIn, cookingTimeIn, serializer);
	}

	public static DifferCookingRecipeBuilder blastingRecipe(Ingredient ingredientIn, ItemLike resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, RecipeSerializer.BLASTING_RECIPE);
	}

	public static DifferCookingRecipeBuilder smeltingRecipe(Ingredient ingredientIn, ItemLike resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, RecipeSerializer.SMELTING_RECIPE);
	}
	
	public static DifferCookingRecipeBuilder smokingRecipe(Ingredient ingredientIn, ItemLike resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, RecipeSerializer.SMOKING_RECIPE);
	}
	
	public static DifferCookingRecipeBuilder bakingRecipe(Ingredient ingredientIn, ItemLike resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn,
				(SimpleCookingSerializer<?>)ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(Mods.ENVIRONMENTAL, "baking")));
	}
	
	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient ingredientIn, ItemLike resultIn) {
		return stonecuttingRecipe(ingredientIn, resultIn, 1);
	}

	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient ingredientIn, ItemLike resultIn, int countIn) {
		return new CuttingRecipeBuilder(RecipeSerializer.STONECUTTER, ingredientIn, resultIn, countIn);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient ingredientIn, ItemLike resultIn) {
		return sawingRecipe(ingredientIn, resultIn, 1);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient ingredientIn, ItemLike resultIn, int countIn) {
		return new CuttingRecipeBuilder(ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(Mods.ENVIRONMENTAL, "sawing")), ingredientIn, resultIn, countIn);
	}
	
	public static void slabCraftingRecipe(Consumer<FinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
	public static void stairsCraftingRecipe(Consumer<FinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
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
	
	public static void wallCraftingRecipe(Consumer<FinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions, String[] flags) {
		shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addModCompat(mods)
    		.addConditions(conditions)
    		.addFlags(flags)
    		.build(consumer);
	}
	
//	public static void verticalSlabCraftingRecipe(Consumer<FinishedRecipe> consumer, Block verticalSlab, Block slab, String[] mods, String[] conditions, String[] flags) {
//		shapedRecipe(verticalSlab, 3)
//    		.patternLine("X")
//    		.patternLine("X")
//    		.patternLine("X")
//    		.key('X', slab)
//    		.addModCompat(mods)
//    		.addConditions(conditions)
//    		.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
//    		.build(consumer);
//	}
//
//	public static void verticalSlabRevertCraftingRecipe(Consumer<FinishedRecipe> consumer, Block slab, Block verticalSlab, String[] mods, String[] conditions, String[] flags) {
//		shapelessRecipe(slab)
//			.addIngredient(verticalSlab.asItem())
//			.addModCompat(mods)
//			.addConditions(conditions)
//			.addFlags(ArrayUtils.add(flags, "vertical_slabs"))
//			.build(consumer);
//	}
}