package co.eltrut.differentiate.common.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class DifferStairsBlock extends StairsBlock {

	public DifferStairsBlock(Supplier<BlockState> state, Properties properties) {
		super(state, properties);
	}

}
