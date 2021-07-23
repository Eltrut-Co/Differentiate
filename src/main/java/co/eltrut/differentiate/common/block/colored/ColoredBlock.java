package co.eltrut.differentiate.common.block.colored;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.block.Block;

public class ColoredBlock extends Block implements IColoredBlock {
	
	private final BlockColor blockColor;
	private final ItemColor itemColor;

	public ColoredBlock(Properties properties, BlockColor blockColor, ItemColor itemColor) {
		super(properties);
		this.blockColor = blockColor;
		this.itemColor = itemColor;
	}

	@Override
	public BlockColor getBlockColor() {
		return this.blockColor;
	}

	@Override
	public ItemColor getItemColor() {
		return this.itemColor;
	}

}
