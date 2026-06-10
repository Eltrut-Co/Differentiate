package co.eltrut.differentiate.common.repo;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class VariantBlocksRepo implements IBlocksRepo {
	
	private DeferredBlock<Block> baseBlock;
	private final DeferredBlock<Block> slabBlock;
	private final DeferredBlock<Block> stairsBlock;
	private final DeferredBlock<Block> wallBlock;
	private final DeferredBlock<Block> verticalSlabBlock;

	private String prefix;
	
	private VariantBlocksRepo(DeferredBlock<Block> baseBlock, DeferredBlock<Block> slabBlock,
							  DeferredBlock<Block> stairsBlock, DeferredBlock<Block> wallBlock,
							  DeferredBlock<Block> verticalSlabBlock) {
		this.baseBlock = baseBlock;
		this.slabBlock = slabBlock;
		this.stairsBlock = stairsBlock;
		this.wallBlock = wallBlock;
		this.verticalSlabBlock = verticalSlabBlock;
	}
	
	public DeferredBlock<Block> getBlock() {
		return this.baseBlock;
	}

	public VariantBlocksRepo setBlock(DeferredBlock<Block> block) {
		this.baseBlock = block;
		return this;
	}

	public VariantBlocksRepo setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}
	
	public DeferredBlock<Block> getSlabBlock() {
		return this.slabBlock;
	}
	
	public DeferredBlock<Block> getStairsBlock() {
		return this.stairsBlock;
	}
	
	public DeferredBlock<Block> getWallBlock() {
		return this.wallBlock;
	}
	
	public DeferredBlock<Block> getVerticalSlabBlock() {
		return this.verticalSlabBlock;
	}

	public List<DeferredBlock<Block>> getBlocksAsList() {
        List<DeferredBlock<Block>> blocks =  new ArrayList<>(List.of(this.slabBlock, this.stairsBlock, this.wallBlock, this.verticalSlabBlock));
		if (this.baseBlock != null) {
			blocks.add(this.baseBlock);
		}
		return blocks;
	}

	public List<DeferredHolder<Block, Block>> getBlocksInOrder() {
		ArrayList<DeferredHolder<Block, Block>> blocks = new ArrayList<>(List.of(this.stairsBlock, this.slabBlock, this.wallBlock));
		if (CompatUtil.areModsLoaded(CompatUtil.Mods.QUARK)) {
			blocks.add(2, this.verticalSlabBlock);
		}
		if (this.baseBlock != null) {
			blocks.addFirst(this.baseBlock);
		}
		return blocks;
	}
	
	public static class Builder {
		
		private DeferredBlock<Block> baseBlock;
		private DeferredBlock<Block> slabBlock;
		private DeferredBlock<Block> stairsBlock;
		private DeferredBlock<Block> wallBlock;
		private DeferredBlock<Block> verticalSlabBlock;
		
		public Builder() {
			this.setAllNull();
		}
		
		public Builder setBlock(DeferredBlock<Block> baseBlock) {
			this.baseBlock = baseBlock;
			return this;
		}
		
		public Builder setSlabBlock(DeferredBlock<Block> slabBlock) {
			this.slabBlock = slabBlock;
			return this;
		}
		
		public Builder setStairsBlock(DeferredBlock<Block> stairsBlock) {
			this.stairsBlock = stairsBlock;
			return this;
		}
		
		public Builder setWallBlock(DeferredBlock<Block> wallBlock) {
			this.wallBlock = wallBlock;
			return this;
		}
		
		public Builder setVerticalSlabBlock(DeferredBlock<Block> verticalSlabBlock) {
			this.verticalSlabBlock = verticalSlabBlock;
			return this;
		}
		
		public VariantBlocksRepo build() {
			return new VariantBlocksRepo(this.baseBlock, this.slabBlock, this.stairsBlock, this.wallBlock,
					this.verticalSlabBlock);
		}
		
		private void setAllNull() {
			this.baseBlock = null;
			this.slabBlock = null;
			this.stairsBlock = null;
			this.wallBlock = null;
			this.verticalSlabBlock = null;
		}
		
	}

}
