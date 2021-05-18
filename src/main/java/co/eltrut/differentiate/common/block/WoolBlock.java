package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.Block;

public class WoolBlock extends Block implements IFlammableBlock {

	public WoolBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}

	@Override
	public int getEncouragement() {
		return FlammableChance.WOOL.getLeft();
	}

	@Override
	public int getFlammability() {
		return FlammableChance.WOOL.getRight();
	}

}
