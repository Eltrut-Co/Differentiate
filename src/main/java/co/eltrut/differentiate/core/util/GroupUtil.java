package co.eltrut.differentiate.core.util;

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
	
	public static class Groups {
		public static final Item.Properties BUILDING_BLOCKS = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
		public static final Item.Properties DECORATIONS = new Item.Properties().group(ItemGroup.DECORATIONS);
		public static final Item.Properties REDSTONE = new Item.Properties().group(ItemGroup.REDSTONE);
		public static final Item.Properties TRANSPORTATION = new Item.Properties().group(ItemGroup.TRANSPORTATION);
		public static final Item.Properties MISCELLANEOUS = new Item.Properties().group(ItemGroup.MISC);
		public static final Item.Properties FOODSTUFFS = new Item.Properties().group(ItemGroup.FOOD);
		public static final Item.Properties TOOLS = new Item.Properties().group(ItemGroup.TOOLS);
		public static final Item.Properties COMBAT = new Item.Properties().group(ItemGroup.COMBAT);
		public static final Item.Properties BREWING = new Item.Properties().group(ItemGroup.BREWING);
	}
	
	public static class SpecialGroups {
		public static final Item.Properties SLABS = Groups.BUILDING_BLOCKS;
		public static final Item.Properties STAIRS = Groups.BUILDING_BLOCKS;
		public static final Item.Properties WALLS = Groups.DECORATIONS;
	}
	
}
