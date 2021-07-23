package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LogSlabBlock extends SlabBlock implements IFlammableBlock {
	
	protected final boolean isNetherWood;
	protected final Supplier<Block> block;
	
	public LogSlabBlock(Properties properties) {
		this(null, properties);
	}
	
	public LogSlabBlock(Properties properties, boolean isNetherWood) {
		this(null, properties, isNetherWood);
	}
	
	public LogSlabBlock(Supplier<Block> strippedBlock, Properties properties) {
		this(strippedBlock, properties, false);
	}
	
	public LogSlabBlock(Supplier<Block> strippedBlock, Properties properties, boolean isNetherWood) {
		super(properties);
		this.block = strippedBlock;
		this.isNetherWood = isNetherWood;
	}
	
//	@Override
//	public BlockState getToolModifiedState(BlockState state, LevelAccessor world, BlockPos pos, Player player, ItemStack stack, ToolType toolType) {
//		if (toolType == ToolType.AXE && this.block != null)
//			return BlockUtil.transferAllBlockStates(state, this.block.get().defaultBlockState());
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
