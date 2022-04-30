package co.eltrut.differentiate.core.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.ArrayUtils;

public class CreativeTabUtil {
	public static Item.Properties getProps(CreativeModeTab tab, String ...mods) {
		for (String mod: mods) {
			if (ModList.get().isLoaded(mod)) {
				return new Item.Properties().tab(tab);
			} else {
				return new Item.Properties();
			}
		}
		return new Item.Properties();
	}
	
	public static class Groups {
		public static final CreativeModeTab SLABS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab STAIRS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		public static final CreativeModeTab WALLS = CreativeModeTab.TAB_DECORATIONS;
		public static final CreativeModeTab VERTICAL_SLABS = CreativeModeTab.TAB_BUILDING_BLOCKS;
		
		public static Item.Properties getSlabProps(String ...mods) {
			return CreativeTabUtil.getProps(SLABS, mods);
		}
		
		public static Item.Properties getStairProps(String ...mods) {
			return CreativeTabUtil.getProps(STAIRS, mods);
		}
		
		public static Item.Properties getWallProps(String ...mods) {
			return CreativeTabUtil.getProps(WALLS, mods);
		}
		
		public static Item.Properties getVerticalSlabProps(String ...mods) {
			return CreativeTabUtil.getProps(VERTICAL_SLABS, ArrayUtils.add(mods, "quark"));
		}
	}
}