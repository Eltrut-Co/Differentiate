package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolType;

public class LogVerticalSlabBlock extends VerticalSlabBlock implements IFlammableBlock {
	
	protected final boolean isNetherWood;
	protected final Supplier<Block> block;
	
	public LogVerticalSlabBlock(Properties properties) {
		this(null, properties);
	}
	
	public LogVerticalSlabBlock(Properties properties, boolean isNetherWood) {
		this(null, properties, true);
	}
	
	public LogVerticalSlabBlock(Supplier<Block> strippedBlock, Properties properties) {
		this(strippedBlock, properties, false);
	}
	
	public LogVerticalSlabBlock(Supplier<Block> strippedBlock, Properties properties, boolean isNetherWood) {
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
