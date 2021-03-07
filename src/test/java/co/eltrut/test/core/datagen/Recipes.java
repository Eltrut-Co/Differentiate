package co.eltrut.test.core.datagen;

import java.util.function.Consumer;

import co.eltrut.differentiate.core.util.DataGenUtil;
import co.eltrut.differentiate.core.util.RecipeUtil;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
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
		
		RecipeUtil.slabCraftingRecipe(consumer, TestBlocks.BLOCK.get(), TestBlocks.BLOCK_TWO.get(), DataGenUtil.EMPTY, DataGenUtil.EMPTY, DataGenUtil.EMPTY);
		RecipeUtil.verticalSlabCraftingRecipe(consumer, TestBlocks.BLOCK_TWO.get(), TestBlocks.BLOCK_THREE.get(), new String[] {Mods.LEPTON}, new String[] {"honey_cookie_tiles", "strawberry_cookie_tiles"}, new String[] {"biotite"});
		
		RecipeUtil.sawingRecipe(Ingredient.fromItems(TestBlocks.BLOCK_THREE.get(), TestBlocks.BLOCK.get()), TestBlocks.BLOCK.get())
			.addModCompat(Mods.ENVIRONMENTAL)
			.build(consumer, new ResourceLocation("test", "sawing/block"));
		
		RecipeUtil.bakingRecipe(Ingredient.fromItems(TestBlocks.BLOCK_TWO.get(), TestBlocks.BLOCK_THREE.get()), TestBlocks.BLOCK.get(), 5, 200)
			.addModCompat(Mods.ENVIRONMENTAL)
			.build(consumer, new ResourceLocation("test", "baking/block"));
	}

}
