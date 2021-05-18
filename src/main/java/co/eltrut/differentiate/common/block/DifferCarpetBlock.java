package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.CarpetBlock;
import net.minecraft.item.DyeColor;

public class DifferCarpetBlock extends CarpetBlock implements IFlammableBlock {

	public DifferCarpetBlock(DyeColor p_i48290_1_, Properties p_i48290_2_) {
		super(p_i48290_1_, p_i48290_2_);
	}

	@Override
	public int getEncouragement() {
		return FlammableChance.CARPET.getLeft();
	}

	@Override
	public int getFlammability() {
		return FlammableChance.CARPET.getRight();
	}

}
