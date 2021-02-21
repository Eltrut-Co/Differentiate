package co.eltrut.test.core.datagen;

import java.util.function.Consumer;

import co.eltrut.differentiate.core.datagen.builder.DifferShapedRecipeBuilder;
import co.eltrut.differentiate.core.util.DataGenUtil;
import co.eltrut.test.core.registry.TestBlocks;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.common.Tags;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		DifferShapedRecipeBuilder.shapedRecipe(Blocks.DIAMOND_BLOCK)
        	.patternLine("xxx")
        	.patternLine("x#x")
        	.patternLine("xxx")
        	.key('x', Blocks.COBBLESTONE)
        	.key('#', Tags.Items.DYES_RED)
        	.setGroup("mytutorial")
        	.addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
        	.setModCompat("quark", "cookielicious")
        	.setConditions("honey_cookie_tiles")
        	.build(consumer);
		
		DataGenUtil.slabCraftingRecipe(consumer, TestBlocks.BLOCK.get(), TestBlocks.BLOCK_TWO.get(), DataGenUtil.EMPTY, DataGenUtil.EMPTY);
	}

}
