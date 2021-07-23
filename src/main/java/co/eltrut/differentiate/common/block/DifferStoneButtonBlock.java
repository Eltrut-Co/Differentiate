package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.StoneButtonBlock;

public class DifferStoneButtonBlock extends StoneButtonBlock {

	public DifferStoneButtonBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.POLISHED_BLACKSTONE_BUTTON, group, items);
	}

}
