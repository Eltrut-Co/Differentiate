package co.eltrut.differentiate.core.util;

import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.recipe.CuttingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.recipe.DifferCookingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.recipe.DifferShapedRecipeBuilder;
import co.eltrut.differentiate.core.datagen.recipe.DifferShapelessRecipeBuilder;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeUtil {
	
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
	
	public static DifferCookingRecipeBuilder cookingRecipe(Ingredient ingredientIn, IItemProvider resultIn,
			float experienceIn, int cookingTimeIn, CookingRecipeSerializer<?> serializer) {
		return new DifferCookingRecipeBuilder(resultIn, ingredientIn, experienceIn, cookingTimeIn, serializer);
	}

	public static DifferCookingRecipeBuilder blastingRecipe(Ingredient ingredientIn, IItemProvider resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, IRecipeSerializer.BLASTING_RECIPE);
	}

	public static DifferCookingRecipeBuilder smeltingRecipe(Ingredient ingredientIn, IItemProvider resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, IRecipeSerializer.SMELTING_RECIPE);
	}
	
	public static DifferCookingRecipeBuilder smokingRecipe(Ingredient ingredientIn, IItemProvider resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn, IRecipeSerializer.SMOKING_RECIPE);
	}
	
	public static DifferCookingRecipeBuilder bakingRecipe(Ingredient ingredientIn, IItemProvider resultIn,
			float experienceIn, int cookingTimeIn) {
		return cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn,
				(CookingRecipeSerializer<?>)ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(Mods.ENVIRONMENTAL, "baking")));
	}
	
	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient ingredientIn, IItemProvider resultIn) {
		return stonecuttingRecipe(ingredientIn, resultIn, 1);
	}

	public static CuttingRecipeBuilder stonecuttingRecipe(Ingredient ingredientIn, IItemProvider resultIn, int countIn) {
		return new CuttingRecipeBuilder(IRecipeSerializer.STONECUTTER, ingredientIn, resultIn, countIn);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient ingredientIn, IItemProvider resultIn) {
		return sawingRecipe(ingredientIn, resultIn, 1);
	}
	
	public static CuttingRecipeBuilder sawingRecipe(Ingredient ingredientIn, IItemProvider resultIn, int countIn) {
		return new CuttingRecipeBuilder(ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(Mods.ENVIRONMENTAL, "sawing")), ingredientIn, resultIn, countIn);
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
