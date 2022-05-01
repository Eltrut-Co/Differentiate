package co.eltrut.differentiate.core.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class TabUtil {
	public static Item.Properties getProps(CreativeModeTab tab, String ... modId) {
		Item.Properties props = new Item.Properties();
		for (String id : modId) {
			return props.tab(ModList.get().isLoaded(id) ? tab : null);
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
}