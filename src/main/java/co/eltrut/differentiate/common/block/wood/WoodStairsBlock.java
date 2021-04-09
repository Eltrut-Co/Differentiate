package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import net.minecraft.block.BlockState;

public class WoodStairsBlock extends DifferStairsBlock implements IFlammableBlock {

	protected final boolean isNetherWood;
	
	public WoodStairsBlock(Supplier<BlockState> state, Properties properties) {
		this(state, properties, false);
	}
	
	public WoodStairsBlock(Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		super(state, properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : 5;
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : 20;
	}

}
