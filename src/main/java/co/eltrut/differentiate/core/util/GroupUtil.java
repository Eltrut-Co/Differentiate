package co.eltrut.differentiate.core.util;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GroupUtil {
	
	public static int getIndex(Item item, NonNullList<ItemStack> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItem() == item) {
				return i;
			}
		}
		return -1;
	}
	
	public static void fillItem(Item item, Item followItem, CreativeModeTab group, NonNullList<ItemStack> items) {
		int index = getIndex(followItem, items);
		if (index != -1) {
			items.add(++index, new ItemStack(item));
		} else {
			items.add(new ItemStack(item));
		}
	}
	
	public static Item.Properties getProps(CreativeModeTab group, String ...mods) {
		return CompatUtil.areModsLoaded(mods) ? new Item.Properties().tab(group) : new Item.Properties().tab(null);
	}
	
	public static class Groups {
		
		public static final CreativeModeTab SLABS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab STAIRS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab WALLS = CreativeModeTab.TAB_DECORATIONS;
		public static final CreativeModeTab VERTICAL_SLABS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		
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
