package co.eltrut.differentiate.common.repo;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class VariantBlocksRepo {
	
	private final DeferredBlock<Block> baseBlock;
	private final DeferredBlock<Block> slabBlock;
	private final DeferredBlock<Block> stairsBlock;
	private final DeferredBlock<Block> wallBlock;
	private final DeferredBlock<Block> verticalSlabBlock;
	
	private VariantBlocksRepo(DeferredBlock<Block> baseBlock, DeferredBlock<Block> slabBlock, DeferredBlock<Block> stairsBlock, DeferredBlock<Block> wallBlock, DeferredBlock<Block> verticalSlabBlock) {
		this.baseBlock = baseBlock;
		this.slabBlock = slabBlock;
		this.stairsBlock = stairsBlock;
		this.wallBlock = wallBlock;
		this.verticalSlabBlock = verticalSlabBlock;
	}
	
	public DeferredBlock<Block> getBlock() {
		return this.baseBlock;
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
			return new VariantBlocksRepo(this.baseBlock, this.slabBlock, this.stairsBlock, this.wallBlock, this.verticalSlabBlock);
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
