package co.eltrut.differentiate.common.blocks.base;

import co.eltrut.differentiate.common.blocks.interf.ICompostableItem;
import net.minecraft.block.SlabBlock;

public class DifferSlabBlock extends SlabBlock implements ICompostableItem {

	public DifferSlabBlock(Properties properties) {
		super(properties);
	}

	@Override
	public float compostableChance() {
		return 0.8F;
	}

}
