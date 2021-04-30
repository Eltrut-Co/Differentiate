package co.eltrut.differentiate.common.block.myalite;

import java.util.function.Supplier;

import co.eltrut.differentiate.client.provider.MyaliteColorProvider;
import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;

public class MyaliteStairsBlock extends DifferStairsBlock implements IColoredBlock {

	public MyaliteStairsBlock(Supplier<BlockState> state, Properties properties) {
		super(state, properties);
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
