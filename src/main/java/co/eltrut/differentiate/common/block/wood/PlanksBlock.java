package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IFuelItem;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class PlanksBlock extends Block implements IFlammableBlock, IFuelItem {

	private final boolean isNetherWood;
	
	public PlanksBlock(Properties properties) {
		this(properties, false);
	}
	
	public PlanksBlock(Properties properties, boolean isNetherWood) {
		super(properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : 5;
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : 5;
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_PLANKS, group, items);
	}

	@Override
	public int getBurnTime() {
		return this.isNetherWood ? 0 : 300;
	}

}
