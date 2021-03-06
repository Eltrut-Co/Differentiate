package co.eltrut.differentiate.common.block.wood;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class LogVerticalSlabBlock extends VerticalSlabBlock implements IFlammableBlock {
	
	private final boolean isNetherWood;
	private final Supplier<Block> block;
	
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
	
	@Override
	public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
		if (toolType == ToolType.AXE && this.block != null)
			return BlockUtil.transferAllBlockStates(state, this.block.get().getDefaultState());
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
