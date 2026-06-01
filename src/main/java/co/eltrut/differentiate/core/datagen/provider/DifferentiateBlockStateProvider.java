package co.eltrut.differentiate.core.datagen.provider;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DifferentiateBlockStateProvider extends BlockStateProvider {

    public DifferentiateBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    protected boolean registerVariantRepoModels(VariantBlocksRepo repo, ResourceLocation texture) {

        DeferredBlock<Block> block = repo.getBlock();
        DeferredBlock<Block> slabBlock = repo.getSlabBlock();
        DeferredBlock<Block> stairsBlock = repo.getStairsBlock();
        DeferredBlock<Block> wallBlock = repo.getWallBlock();
        DeferredBlock<Block> verticalSlabBlock = repo.getVerticalSlabBlock();

        if (block != null) {
            String blockName = BlockUtil.getIdFromBlock(block.get());
            this.simpleBlockWithItem(block.get(), models().cubeAll(blockName, texture));
        }
        if (slabBlock != null) {
            String slabName = BlockUtil.getIdFromBlock(slabBlock.get());
            this.slabBlock((SlabBlock) slabBlock.get(), texture, texture);
            this.simpleBlockItem(slabBlock.get(), models().slab(slabName, texture, texture, texture));
        }
        if (stairsBlock != null) {
            String stairsName = BlockUtil.getIdFromBlock(stairsBlock.get());
            this.stairsBlock((StairBlock) stairsBlock.get(), texture);
            this.simpleBlockItem(stairsBlock.get(), models().stairs(stairsName, texture, texture, texture));
        }
        if (wallBlock != null) {
            String wallName = BlockUtil.getIdFromBlock(wallBlock.get());
            this.wallBlock((WallBlock) wallBlock.get(), texture);
            this.simpleBlockItem(wallBlock.get(), models().wallInventory(wallName + "_inventory", texture));
        }
        if (verticalSlabBlock != null) {
            // TODO: i have no idea how to build this
        }
        return true;

    }

    @Override
    protected void registerStatesAndModels() {

    }
}
