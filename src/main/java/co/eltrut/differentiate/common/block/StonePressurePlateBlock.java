package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.PressurePlateBlock;

public class StonePressurePlateBlock extends PressurePlateBlock {

	public StonePressurePlateBlock(Properties propertiesIn) {
		super(Sensitivity.MOBS, propertiesIn);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.POLISHED_BLACKSTONE_PRESSURE_PLATE, group, items);
	}

}
