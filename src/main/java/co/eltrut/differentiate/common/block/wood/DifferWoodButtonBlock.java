package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WoodButtonBlock;

public class DifferWoodButtonBlock extends WoodButtonBlock {

	public DifferWoodButtonBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_BUTTON, group, items);
	}

}
