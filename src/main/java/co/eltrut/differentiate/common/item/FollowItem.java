package co.eltrut.differentiate.common.item;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FollowItem extends Item {
	
	private final Item followItem;

	public FollowItem(Properties properties, Item followItem) {
		super(properties);
		this.followItem = followItem;
	}
	
	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), this.followItem, group, items);
	}

}
