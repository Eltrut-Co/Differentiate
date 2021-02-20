package co.eltrut.differentiate.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class FuelBlockItem extends BlockItem {
	
	private final int burnTime;
	
	public FuelBlockItem(Block blockIn, Properties builder, int burnTime) {
		super(blockIn, builder);
		this.burnTime = burnTime;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return this.burnTime;
	}

}
