package co.eltrut.differentiate.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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
