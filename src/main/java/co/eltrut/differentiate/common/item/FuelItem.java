package co.eltrut.differentiate.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FuelItem extends Item {

	private final int burnTime;
	
	public FuelItem(Properties builder, int burnTime) {
		super(builder);
		this.burnTime = burnTime;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return this.burnTime;
	}
	
}
