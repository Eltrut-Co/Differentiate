package co.eltrut.differentiate.core.util;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GroupUtil {
	
	public static int getIndex(Item item, NonNullList<ItemStack> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItem() == item) {
				return i;
			}
		}
		return -1;
	}
	
	public static void fillItem(Item item, Item followItem, ItemGroup group, NonNullList<ItemStack> items) {
		int index = getIndex(followItem, items);
		if (index != -1) {
			items.add(++index, new ItemStack(item));
		} else {
			items.add(new ItemStack(item));
		}
	}
	
	public static Item.Properties getProps(ItemGroup group, String ...mods) {
		return CompatUtil.areModsLoaded(mods) ? new Item.Properties().tab(group) : new Item.Properties().tab(null);
	}
	
	public static class Groups {
		
		public static final ItemGroup SLABS = ItemGroup.TAB_BUILDING_BLOCKS;
		public static final ItemGroup STAIRS = ItemGroup.TAB_BUILDING_BLOCKS;
		public static final ItemGroup WALLS = ItemGroup.TAB_DECORATIONS;
		public static final ItemGroup VERTICAL_SLABS = ItemGroup.TAB_BUILDING_BLOCKS;
		
		public static Item.Properties getSlabProps(String ...mods) {
			return GroupUtil.getProps(SLABS, mods);
		}
		
		public static Item.Properties getStairProps(String ...mods) {
			return GroupUtil.getProps(STAIRS, mods);
		}
		
		public static Item.Properties getWallProps(String ...mods) {
			return GroupUtil.getProps(WALLS, mods);
		}
		
		public static Item.Properties getVerticalSlabProps(String ...mods) {
			return GroupUtil.getProps(VERTICAL_SLABS, ArrayUtils.add(mods, "quark"));
		}
		
	}
	
}
