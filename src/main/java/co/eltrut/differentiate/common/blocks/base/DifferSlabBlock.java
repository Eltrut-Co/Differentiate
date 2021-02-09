package co.eltrut.differentiate.common.blocks.base;

import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemGroup;

public class DifferSlabBlock extends SlabBlock implements IItemGroupBlock {

	public DifferSlabBlock(Properties properties) {
		super(properties);
	}

	@Override
	public ItemGroup group() {
		return ItemGroup.BUILDING_BLOCKS;
	}

}
