package co.eltrut.differentiate.common.item;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FollowItem extends Item {
	
	private final Item followItem;

	public FollowItem(Properties properties, Item followItem) {
		super(properties);
		this.followItem = followItem;
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), this.followItem, group, items);
	}

}
