package co.eltrut.differentiate.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class DifferBlockItem extends BlockItem {
	
	private final int burnTime;
	
	public DifferBlockItem(Block blockIn, Properties builder) {
		this(blockIn, builder, 0);
	}
	
	public DifferBlockItem(Block blockIn, Properties builder, int burnTime) {
		super(blockIn, builder);
		this.burnTime = burnTime;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return this.burnTime;
	}

}
