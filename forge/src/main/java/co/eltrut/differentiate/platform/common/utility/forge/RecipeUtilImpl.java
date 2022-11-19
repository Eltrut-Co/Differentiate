package co.eltrut.differentiate.platform.common.utility.forge;

import co.eltrut.differentiate.core.datagen.builder.CuttingRecipeBuilder;
import co.eltrut.differentiate.core.datagen.builder.DifferCookingRecipeBuilder;
import co.eltrut.differentiate.platform.common.utility.CompatUtil;
import co.eltrut.differentiate.platform.common.utility.RecipeUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeUtilImpl {
    public static DifferCookingRecipeBuilder bakingRecipe(Ingredient ingredientIn, ItemLike resultIn, float experienceIn, int cookingTimeIn) {
        return RecipeUtil.cookingRecipe(ingredientIn, resultIn, experienceIn, cookingTimeIn,
                (SimpleCookingSerializer<?>) ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(CompatUtil.Mods.ENVIRONMENTAL, "baking")));
    }

    public static CuttingRecipeBuilder sawingRecipe(Ingredient ingredientIn, ItemLike resultIn, int countIn) {
        return new CuttingRecipeBuilder(ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(CompatUtil.Mods.ENVIRONMENTAL, "sawing")), ingredientIn, resultIn, countIn);
    }
}