package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WoodStairBlock extends StairBlock implements IFlammableBlock {

	protected final boolean isNetherWood;
	
	public WoodStairBlock(Supplier<BlockState> state, Properties properties) {
		this(state, properties, false);
	}
	
	public WoodStairBlock(Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		super(state, properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getRight();
	}

}
