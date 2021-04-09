package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import net.minecraft.block.SlabBlock;

public class WoodSlabBlock extends SlabBlock implements IFlammableBlock {

	protected final boolean isNetherWood;
	
	public WoodSlabBlock(Properties properties) {
		this(properties, false);
	}
	
	public WoodSlabBlock(Properties properties, boolean isNetherWood) {
		super(properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0: 5;
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : 20;
	}

}
