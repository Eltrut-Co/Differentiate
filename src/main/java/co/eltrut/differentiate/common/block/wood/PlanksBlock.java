package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class PlanksBlock extends Block implements IFlammableBlock {

	protected final boolean isNetherWood;
	
	public PlanksBlock(Properties properties) {
		this(properties, false);
	}
	
	public PlanksBlock(Properties properties, boolean isNetherWood) {
		super(properties);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getRight();
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_PLANKS, group, items);
	}

}
