package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LogStairBlock extends StairBlock implements IFlammableBlock {
	
	protected final boolean isNetherWood;
	protected final Supplier<Block> block;

	public LogStairBlock(Supplier<BlockState> state, Properties properties) {
		this(null, state, properties);
	}
	
	public LogStairBlock(Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		this(null, state, properties, isNetherWood);
	}
	
	public LogStairBlock(Supplier<Block> strippedBlock, Supplier<BlockState> state, Properties properties) {
		this(strippedBlock, state, properties, false);
	}
	
	public LogStairBlock(Supplier<Block> strippedBlock, Supplier<BlockState> state, Properties properties, boolean isNetherWood) {
		super(state, properties);
		this.block = strippedBlock;
		this.isNetherWood = isNetherWood;
	}
	
//	@Override
//	public BlockState getToolModifiedState(BlockState state, LevelAccessor world, BlockPos pos, Player player, ItemStack stack, ToolType toolType) {
//		if (toolType == ToolType.AXE && this.block != null)
//			return this.withPropertiesOf(state);
//		return super.getToolModifiedState(state, world, pos, player, stack, toolType);
//	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getRight();
	}
	
}
