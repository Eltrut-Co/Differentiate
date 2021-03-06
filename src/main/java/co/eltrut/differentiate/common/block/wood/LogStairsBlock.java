package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class LogStairsBlock extends DifferStairsBlock {
	private final Supplier<Block> block;

	public LogStairsBlock(Supplier<Block> strippedBlock, Supplier<BlockState> state, Properties properties) {
		super(state, properties);
		this.block = strippedBlock;
	}
	
	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.AXE)
			return BlockUtil.transferAllBlockStates(state, this.block.get().getDefaultState());
		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}
}
