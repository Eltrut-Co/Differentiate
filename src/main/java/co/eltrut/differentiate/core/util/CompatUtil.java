package co.eltrut.differentiate.core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

public class CompatUtil {
	
	public static boolean areModsLoaded(String ...mods) {
		if (mods == null) return true;
		for (String mod : mods) {
			if (mod.startsWith("!") && ModList.get().isLoaded(mod.substring(1))) {
				return false;
			} else if (!ModList.get().isLoaded(mod)) {
				return false;
			}
		}
		return true;
	}

	public static Block getBlock(String mod, String blockName) {
		return areModsLoaded(mod) ? BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(mod, blockName)) : null;
	}

	public static Item getItem(String mod, String itemName) {
		return areModsLoaded(mod) ? BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(mod, itemName)) : null;
	}
	
	public static class Mods {
		// Core
		public static final String MINECRAFT = "minecraft";
		public static final String NEOFORGE = "neoforge";
		public static final String VANILLA_BACKPORT = "vanillabackport";
		
		// Vazkii
		public static final String QUARK = "quark";
		public static final String ZETA = "zeta";
		
		// Abnormals
		public static final String ABNORMALS_DELIGHT = "abnormals_delight";
		public static final String ALLUREMENT = "allurement";
		public static final String ATMOSPHERIC = "atmospheric";
		public static final String AUTUMNITY = "autumnity";
		public static final String BERRY_GOOD = "berry_good";
		public static final String BLUEPRINT = "blueprint";
		public static final String BOATLOAD = "boatload";
		public static final String BUZZIER_BEES = "buzzier_bees";
		public static final String CAVERNS_AND_CHASMS = "caverns_and_chasms";
		public static final String CLAYWORKS = "clayworks";
		public static final String ENDERGETIC_EXPANSION = "endergetic";
		public static final String ENVIRONMENTAL = "environmental";
		public static final String GALLERY = "gallery";
		public static final String INCUBATION = "incubation";
		public static final String NEAPOLITAN = "neapolitan";
		public static final String NETHER_EXTENSION = "nether_extension";
		public static final String PERSONALITY = "personality";
		public static final String PET_CEMETERY = "pet_cemetery";
		public static final String SAVAGE_AND_RAVAGE = "savage_and_ravage";
		public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
		public static final String WOODWORKS = "woodworks";
		
		// Aurora
		public static final String ENHANCED_MUSHROOMS = "enhanced_mushrooms";
		
		// Evoslab
		public static final String COOKIELICIOUS = "cookielicious";
		
		// Eltrut & Co.
		public static final String ADDENDUM = "addendum";
		public static final String DIFFERENTIATE = "differentiate";
		public static final String LEPTON = "lepton";
		public static final String MORE_RESPAWN_ANCHORS = "morerespawnanchors";

		// Every Compat
		public static final String EVERY_COMPAT = "everycomp";
		public static final String STONE_ZONE = "stonezone";

		// Other
		public static final String CREATE = "create";
		public static final String DYE_DEPOT = "dye_depot";
		public static final String DYE_THE_WORLD = "dye_the_world";
		public static final String FARMERS_DELIGHT = "farmersdelight";
		public static final String SUPPLEMENTARIES = "supplementaries";
	}
	
}
