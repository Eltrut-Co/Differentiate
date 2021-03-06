package co.eltrut.differentiate.common.repo;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public class VariantBlocksRepo {
	
	private final RegistryObject<Block> baseBlock;
	private final RegistryObject<Block> slabBlock;
	private final RegistryObject<Block> stairsBlock;
	private final RegistryObject<Block> wallBlock;
	private final RegistryObject<Block> verticalSlabBlock;
	
	private VariantBlocksRepo(RegistryObject<Block> baseBlock, RegistryObject<Block> slabBlock, RegistryObject<Block> stairsBlock, RegistryObject<Block> wallBlock, RegistryObject<Block> verticalSlabBlock) {
		this.baseBlock = baseBlock;
		this.slabBlock = slabBlock;
		this.stairsBlock = stairsBlock;
		this.wallBlock = wallBlock;
		this.verticalSlabBlock = verticalSlabBlock;
	}
	
	public RegistryObject<Block> getBlock() {
		return this.baseBlock;
	}
	
	public RegistryObject<Block> getSlabBlock() {
		return this.slabBlock;
	}
	
	public RegistryObject<Block> getStairsBlock() {
		return this.stairsBlock;
	}
	
	public RegistryObject<Block> getWallBlock() {
		return this.wallBlock;
	}
	
	public RegistryObject<Block> getVerticalSlabBlock() {
		return this.verticalSlabBlock;
	}
	
	public static class Builder {
		
		private RegistryObject<Block> baseBlock;
		private RegistryObject<Block> slabBlock;
		private RegistryObject<Block> stairsBlock;
		private RegistryObject<Block> wallBlock;
		private RegistryObject<Block> verticalSlabBlock;
		
		public Builder() {
			this.setAllNull();
		}
		
		public Builder setBlock(RegistryObject<Block> baseBlock) {
			this.baseBlock = baseBlock;
			return this;
		}
		
		public Builder setSlabBlock(RegistryObject<Block> slabBlock) {
			this.slabBlock = slabBlock;
			return this;
		}
		
		public Builder setStairsBlock(RegistryObject<Block> stairsBlock) {
			this.stairsBlock = stairsBlock;
			return this;
		}
		
		public Builder setWallBlock(RegistryObject<Block> wallBlock) {
			this.wallBlock = wallBlock;
			return this;
		}
		
		public Builder setVerticalSlabBlock(RegistryObject<Block> verticalSlabBlock) {
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
