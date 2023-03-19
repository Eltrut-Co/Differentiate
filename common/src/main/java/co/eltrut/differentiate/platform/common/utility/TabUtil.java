package co.eltrut.differentiate.platform.common.utility;

import co.eltrut.differentiate.platform.Environment;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TabUtil {
	public static Item.Properties getProps(CreativeModeTab tab, String ... modId) {
		Item.Properties props = new Item.Properties();
		for (String id : modId) {
			return props.tab(Environment.isLoaded(id) ? tab : null);
		}
		return props;
	}

	public static class Tabs {
		public static final CreativeModeTab SLABS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab STAIRS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab WALLS = CreativeModeTab.TAB_DECORATIONS;

		public static Item.Properties getSlabProps(String modId) {
			return TabUtil.getProps(SLABS, modId);
		}
		
		public static Item.Properties getStairProps(String modId) {
			return TabUtil.getProps(STAIRS, modId);
		}
		
		public static Item.Properties getWallProps(String modId) {
			return TabUtil.getProps(WALLS, modId);
		}
	}

	private static Pair<Item, ItemStack[]> INSERT_PAIR;

	public static void setInsertItemTab(Item firstItem, ItemStack... insertedItem) {
		INSERT_PAIR = new Pair<>(firstItem, insertedItem);
	}

	public static Pair<Item, ItemStack[]> getInsertItemTab() {
		return INSERT_PAIR;
	}
}