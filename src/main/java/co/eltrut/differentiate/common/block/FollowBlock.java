package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FollowBlock extends Block {
	
	private final Block followBlock;

	public FollowBlock(Properties properties, Block followBlock) {
		super(properties);
		this.followBlock = followBlock;
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), followBlock.asItem(), group, items);
	}

}
