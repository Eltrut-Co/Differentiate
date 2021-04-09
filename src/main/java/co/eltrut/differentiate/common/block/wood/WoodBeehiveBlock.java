package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import net.minecraft.block.BeehiveBlock;

public class WoodBeehiveBlock extends BeehiveBlock implements IFlammableBlock {

	protected final boolean isNetherWood;
	
	public WoodBeehiveBlock(Properties properties) {
		this(properties, false);
	}
	
	public WoodBeehiveBlock(Properties properties, boolean isNetherWood) {
		super(properties);
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
