package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class DifferWoodButtonBlock extends WoodButtonBlock {

	public DifferWoodButtonBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_BUTTON, group, items);
	}

}
