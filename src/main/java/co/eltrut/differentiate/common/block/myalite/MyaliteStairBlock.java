package co.eltrut.differentiate.common.block.myalite;

import java.util.function.Supplier;

import co.eltrut.differentiate.client.provider.MyaliteColorProvider;
import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MyaliteStairBlock extends StairBlock implements IColoredBlock {

	public MyaliteStairBlock(Supplier<BlockState> state, Properties properties) {
		super(state, properties);
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
