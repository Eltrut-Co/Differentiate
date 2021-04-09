package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class LogStairsBlock extends DifferStairsBlock implements IFlammableBlock {
	
	protected final boolean isNetherWood;
	protected final Supplier<Block> block;

	public LogStairsBlock(Supplier<BlockState> state, Properties properties) {
		this(null, state, properties);
	}
	
	public LogStairsBlock(Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		this(null, state, properties, isNetherWood);
	}
	
	public LogStairsBlock(Supplier<Block> strippedBlock, Supplier<BlockState> state, Properties properties) {
		this(strippedBlock, state, properties, false);
	}
	
	public LogStairsBlock(Supplier<Block> strippedBlock, Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		super(state, properties);
		this.block = strippedBlock;
		this.isNetherWood = isNetherWood;
	}
	
	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.AXE && this.block != null)
			return BlockUtil.transferAllBlockStates(state, this.block.get().defaultBlockState());
		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : 5;
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : 5;
	}
	
}
