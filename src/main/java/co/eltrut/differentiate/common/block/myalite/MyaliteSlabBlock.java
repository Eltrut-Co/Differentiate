package co.eltrut.differentiate.common.block.myalite;

import co.eltrut.differentiate.client.provider.MyaliteColorProvider;
import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;

public class MyaliteSlabBlock extends SlabBlock implements IColoredBlock {

	public MyaliteSlabBlock(Properties p_i48331_1_) {
		super(p_i48331_1_);
	}

	@Override
	public IItemColor getItemColor() {
		return MyaliteColorProvider.getItemColor();
	}

	@Override
	public IBlockColor getBlockColor() {
		return MyaliteColorProvider.getBlockColor();
	}

}
