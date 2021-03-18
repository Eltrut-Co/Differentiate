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
		
		public static Item.Properties slabs(String ...mods) {
			return GroupUtil.getProps(ItemGroup.TAB_BUILDING_BLOCKS, mods);
		}
		
		public static Item.Properties stairs(String ...mods) {
			return GroupUtil.getProps(ItemGroup.TAB_BUILDING_BLOCKS, mods);
		}
		
		public static Item.Properties walls(String ...mods) {
			return GroupUtil.getProps(ItemGroup.TAB_DECORATIONS, mods);
		}
		
		public static Item.Properties vertSlabs(String ...mods) {
			return GroupUtil.getProps(ItemGroup.TAB_BUILDING_BLOCKS, ArrayUtils.add(mods, "quark"));
		}
		
	}
	
}
