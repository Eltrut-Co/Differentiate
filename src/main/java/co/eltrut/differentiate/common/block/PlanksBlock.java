package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import net.minecraft.block.Block;

public class PlanksBlock extends Block implements IFlammableBlock {

	private final boolean isNetherWood;
	
	public PlanksBlock(Properties properties) {
		this(properties, false);
	}
	
	public PlanksBlock(Properties properties, boolean isNetherWood) {
		super(properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : 5;
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : 5;
	}

}
