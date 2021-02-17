package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;

public class ColoredBlock extends Block implements IColoredBlock {
	
	private final IBlockColor blockColor;
	private final IItemColor itemColor;

	public ColoredBlock(Properties properties, IBlockColor blockColor, IItemColor itemColor) {
		super(properties);
		this.blockColor = blockColor;
		this.itemColor = itemColor;
	}

	@Override
	public IBlockColor getBlockColor() {
		return this.blockColor;
	}

	@Override
	public IItemColor getItemColor() {
		return this.itemColor;
	}

}
