package co.eltrut.test.core.datagen;

import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import co.eltrut.differentiate.core.util.RecipeUtil;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.apache.commons.lang3.ArrayUtils;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		RecipeUtil.shapedRecipe(Blocks.DIAMOND_BLOCK)
        	.patternLine("xxx")
        	.patternLine("x#x")
        	.patternLine("xxx")
        	.key('x', Blocks.COBBLESTONE)
        	.key('#', Tags.Items.DYES_RED)
        	.setGroup("mytutorial")
        	.addModCompat(Mods.QUARK, Mods.COOKIELICIOUS)
        	.addConditions("honey_cookie_tiles")
        	.build(consumer);
		
		RecipeUtil.slabCraftingRecipe(consumer, TestBlocks.BLOCK.get(), TestBlocks.BLOCK_TWO.get(), ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.EMPTY_STRING_ARRAY);
		RecipeUtil.verticalSlabCraftingRecipe(consumer, TestBlocks.BLOCK_TWO.get(), TestBlocks.BLOCK_THREE.get(), new String[] {Mods.LEPTON}, new String[] {"honey_cookie_tiles", "strawberry_cookie_tiles"}, new String[] {"biotite"});
		
		RecipeUtil.sawingRecipe(Ingredient.of(TestBlocks.BLOCK_THREE.get(), TestBlocks.BLOCK.get()), TestBlocks.BLOCK.get())
			.addModCompat(Mods.ENVIRONMENTAL)
			.build(consumer, new ResourceLocation("test", "sawing/block"));
		
		RecipeUtil.bakingRecipe(Ingredient.of(TestBlocks.BLOCK_TWO.get(), TestBlocks.BLOCK_THREE.get()), TestBlocks.BLOCK.get(), 5, 200)
			.addModCompat(Mods.ENVIRONMENTAL)
			.build(consumer, new ResourceLocation("test", "baking/block"));
	}

}
