package co.eltrut.differentiate.common.block.myalite;

import co.eltrut.differentiate.client.provider.MyaliteColorProvider;
import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.block.SlabBlock;

public class MyaliteSlabBlock extends SlabBlock implements IColoredBlock {

	public MyaliteSlabBlock(Properties p_i48331_1_) {
		super(p_i48331_1_);
	}

	@Override
	public ItemColor getItemColor() {
		return MyaliteColorProvider.getItemColor();
	}

	@Override
	public BlockColor getBlockColor() {
		return MyaliteColorProvider.getBlockColor();
	}

}
