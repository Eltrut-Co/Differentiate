package co.eltrut.differentiate.common.repo;

import net.minecraft.world.level.block.Block;
import java.util.function.Supplier;

public record VariantBlocksRepo(
		Supplier<Block> baseBlock,
		Supplier<Block> slabBlock,
		Supplier<Block> stairsBlock,
		Supplier<Block> wallBlock,
		Supplier<Block> verticalSlabBlock) {

	public Supplier<Block> getBlock() {
		return this.baseBlock;
	}

	public Supplier<Block> getSlabBlock() {
		return this.slabBlock;
	}

	public Supplier<Block> getStairsBlock() {
		return this.stairsBlock;
	}

	public Supplier<Block> getWallBlock() {
		return this.wallBlock;
	}

	public Supplier<Block> getVerticalSlabBlock() {
		return this.verticalSlabBlock;
	}

	public static class Builder {
		private Supplier<Block> baseBlock;
		private Supplier<Block> slabBlock;
		private Supplier<Block> stairsBlock;
		private Supplier<Block> wallBlock;
		private Supplier<Block> verticalSlabBlock;

		public Builder() {
			this.setAllNull();
		}

		public Builder setBlock(Supplier<Block> baseBlock) {
			this.baseBlock = baseBlock;
			return this;
		}

		public Builder setSlabBlock(Supplier<Block> slabBlock) {
			this.slabBlock = slabBlock;
			return this;
		}

		public Builder setStairsBlock(Supplier<Block> stairsBlock) {
			this.stairsBlock = stairsBlock;
			return this;
		}

		public Builder setWallBlock(Supplier<Block> wallBlock) {
			this.wallBlock = wallBlock;
			return this;
		}

		public Builder setVerticalSlabBlock(Supplier<Block> verticalSlabBlock) {
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