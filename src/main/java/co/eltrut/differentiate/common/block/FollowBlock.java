package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class FollowBlock extends Block {
	
	private final Item followItem;

	public FollowBlock(Properties properties, Item followItem) {
		super(properties);
		this.followItem = followItem;
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), followItem, group, items);
	}

}
