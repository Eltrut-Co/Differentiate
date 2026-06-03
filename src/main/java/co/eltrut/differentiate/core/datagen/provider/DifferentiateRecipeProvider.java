package co.eltrut.differentiate.core.datagen.provider;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DifferentiateRecipeProvider extends RecipeProvider {

    protected final String modid;

    public DifferentiateRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modid) {
        super(output, registries);
        this.modid = modid;
    }

    protected boolean buildStonecuttingRecipes(List<Block> blocks, VariantBlocksRepo repo, RecipeOutput output, String ...mods) {
        if (blocks.isEmpty())
            return false;

        Set<ICondition> conditionsSet = Arrays.stream(mods).map(ModLoadedCondition::new).collect(Collectors.toSet());
        ICondition[] conditions = conditionsSet.toArray(ICondition[]::new);
        if (!Arrays.asList(mods).contains(CompatUtil.Mods.QUARK))
            conditionsSet.add(new ModLoadedCondition(CompatUtil.Mods.QUARK));
        ICondition[] conditionsWithQuark = conditionsSet.toArray(ICondition[]::new);

        for (Block block : blocks) {
            String name = BlockUtil.getIdFromBlock(block);
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, repo.getSlabBlock(), 2)
                    .unlockedBy("has_" + name, has(block))
                    .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + name + "_slab"));
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, repo.getStairsBlock())
                    .unlockedBy("has_" + name, has(block))
                    .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + name + "_stairs"));
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, repo.getWallBlock())
                    .unlockedBy("has_" + name, has(block))
                    .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + name + "_wall"));
            SingleItemRecipeBuilder.stonecutting(Ingredient.of(block), RecipeCategory.BUILDING_BLOCKS, repo.getVerticalSlabBlock(), 2)
                    .unlockedBy("has_" + name, has(block))
                    .save(output.withConditions(conditionsWithQuark), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + name + "_vertical_slab"));
        }
        return true;
    }
}
