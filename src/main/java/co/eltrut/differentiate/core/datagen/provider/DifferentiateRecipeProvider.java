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
import net.minecraft.world.level.block.Blocks;
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

        Ingredient ingredient = Ingredient.of(blocks.toArray(new Block[0]));

        if (repo.getBlock() != null) {
            Ingredient ingredientWithoutBlock = Ingredient.of(blocks.stream().filter(s -> !s.equals(repo.getBlock().get())).toArray(Block[]::new));
            SingleItemRecipeBuilder.stonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, repo.getBlock())
                    .unlockedBy("has_block", has(Blocks.STONE)) // this doesn't matter since we don't use it
                    .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + BlockUtil.getIdFromBlock(repo.getBlock().get())));
        }

        SingleItemRecipeBuilder.stonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, repo.getSlabBlock(), 2)
                .unlockedBy("has_block", has(Blocks.STONE)) // this doesn't matter since we don't use it
                .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + BlockUtil.getIdFromBlock(repo.getSlabBlock().get())));
        SingleItemRecipeBuilder.stonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, repo.getStairsBlock())
                .unlockedBy("has_block", has(Blocks.STONE))
                .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + BlockUtil.getIdFromBlock(repo.getStairsBlock().get())));
        SingleItemRecipeBuilder.stonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, repo.getWallBlock())
                .unlockedBy("has_block", has(Blocks.STONE))
                .save(output.withConditions(conditions), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + BlockUtil.getIdFromBlock(repo.getWallBlock().get())));
        SingleItemRecipeBuilder.stonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, repo.getVerticalSlabBlock(), 2)
                .unlockedBy("has_block", has(Blocks.STONE))
                .save(output.withConditions(conditionsWithQuark), ResourceLocation.fromNamespaceAndPath(this.modid, "stonecutting/" + BlockUtil.getIdFromBlock(repo.getVerticalSlabBlock().get())));
        return true;
    }
}
