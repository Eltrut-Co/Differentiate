package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class LogWallBlock extends WallBlock implements IFlammableBlock {
	
	protected final boolean isNetherWood;
	protected final Supplier<Block> block;
	
	public LogWallBlock(Properties properties) {
		this(null, properties);
	}
	
	public LogWallBlock(Properties properties, boolean isNetherWood) {
		this(null, properties, isNetherWood);
	}
	
	public LogWallBlock(Supplier<Block> strippedBlock, Properties properties) {
		this(strippedBlock, properties, false);
	}
	
	public LogWallBlock(Supplier<Block> strippedBlock, Properties properties, boolean isNetherWood) {
		super(properties);
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
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getRight();
	}
	
}
