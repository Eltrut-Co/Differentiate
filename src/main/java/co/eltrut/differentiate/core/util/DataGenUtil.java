package co.eltrut.differentiate.core.util;

import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import co.eltrut.differentiate.core.datagen.builder.DifferShapedRecipeBuilder;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.IFinishedRecipe;

public class DataGenUtil {
	
	public static final String[] EMPTY = new String[0];
	
	public static void addProviders(DataGenerator generator, IDataProvider ...providers) {
		for (IDataProvider provider : providers) {
			generator.addProvider(provider);
		}
	}
	
	public static void slabCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.key('X', parent)
    		.addCriterion("item", InventoryChangeTrigger.Instance.forItems(parent))
    		.setModCompat(mods)
    		.setConditions(conditions)
    		.build(consumer);
	}
	
	public static void stairsCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 4)
    		.patternLine("X  ")
    		.patternLine("XX ")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addCriterion("item", InventoryChangeTrigger.Instance.forItems(parent))
    		.setModCompat(mods)
    		.setConditions(conditions)
    		.build(consumer);
	}
	
	public static void wallCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block result, Block parent, String[] mods, String[] conditions) {
		DifferShapedRecipeBuilder.shapedRecipe(result, 6)
    		.patternLine("XXX")
    		.patternLine("XXX")
    		.key('X', parent)
    		.addCriterion("item", InventoryChangeTrigger.Instance.forItems(parent))
    		.setModCompat(mods)
    		.setConditions(conditions)
    		.build(consumer);
	}
	
	public static void verticalSlabCraftingRecipe(Consumer<IFinishedRecipe> consumer, Block verticalSlab, Block slab, String[] mods, String[] conditions) {
		DifferShapedRecipeBuilder.shapedRecipe(verticalSlab, 3)
    		.patternLine("X  ")
    		.patternLine("X  ")
    		.patternLine("X  ")
    		.key('X', slab)
    		.addCriterion("item", InventoryChangeTrigger.Instance.forItems(slab))
    		.setModCompat(ArrayUtils.add(mods, "quark"))
    		.setConditions(conditions)
    		.build(consumer);
	}
	
	// TODO: vertical slab revert recipe
	
}
