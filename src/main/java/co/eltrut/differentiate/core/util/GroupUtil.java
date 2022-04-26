package co.eltrut.differentiate.core.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class GroupUtil {
	public static Item.Properties getProps(CreativeModeTab group, String ...mods) {
		return ModList.get().isLoaded(Arrays.toString(mods)) ? new Item.Properties().tab(group) : new Item.Properties().tab(null);
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