package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.block.DifferPressurePlateBlock;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class WoodPressurePlateBlock extends DifferPressurePlateBlock {

	public WoodPressurePlateBlock(Properties propertiesIn) {
		super(Sensitivity.EVERYTHING, propertiesIn);
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_PRESSURE_PLATE, group, items);
	}

}
