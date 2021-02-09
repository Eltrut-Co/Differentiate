package co.eltrut.differentiate.core.util;

import net.minecraftforge.fml.ModList;

public class CompatUtil {
	
	public static boolean areModsLoaded(String ...mods) {
		for (String mod : mods)
			if (!ModList.get().isLoaded(mod))
				return false;
		return true;
	}
	
	public static class Mods {
		// Vazkii
		public static final String QUARK = "quark";
		
		// Abnormals
		public static final String ATMOSPHERIC = "atmospheric";
		public static final String AUTUMNITY = "autumnity";
		public static final String BAMBOO_BLOCKS = "bamboo_blocks";
		public static final String BERRY_GOOD = "berry_good";
		public static final String BUZZIER_BEES = "buzzier_bees";
		public static final String ENDERGETIC_EXPANSION = "endergetic";
		public static final String ENVIRONMENTAL = "environmental";
		public static final String EXTRA_BOATS = "extraboats";
		public static final String NEAPOLITAN = "neapolitan";
		public static final String NETHER_EXTENSION = "nether_extension";
		public static final String SAVAGE_AND_RAVAGE = "savageandravage";
		public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
		
		// Aurora
		public static final String BETTER_BADLANDS = "better_badlands";
		public static final String ENHANCED_MUSHROOMS = "enhanced_mushrooms";
		public static final String FRUITFUL	= "fruitful";
		
		// Others
	}
	
}
