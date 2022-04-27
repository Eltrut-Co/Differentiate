package co.eltrut.differentiate.core.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class PropertyUtil {
	public static String getPrefix(String name) {
		return name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
	}
	
	public static class QuarkProperties {
		public static final Block.Properties SOUL_SANDSTONE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(0.8F);
		public static final Block.Properties MIDORI = Block.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final Block.Properties LIMESTONE = Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final Block.Properties JASPER = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final Block.Properties SLATE = Block.Properties.of(Material.STONE, MaterialColor.ICE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final Block.Properties MYALITE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
	}

	public static class WoodProperties {
		public static final MaterialColor ACACIA = MaterialColor.COLOR_GRAY;
		public static final MaterialColor BIRCH = MaterialColor.SAND;
		public static final MaterialColor DARK_OAK = MaterialColor.COLOR_BROWN;
		public static final MaterialColor JUNGLE = MaterialColor.DIRT;
		public static final MaterialColor OAK = MaterialColor.WOOD;
		public static final MaterialColor SPRUCE = MaterialColor.PODZOL;
		public static final MaterialColor CRIMSON = MaterialColor.CRIMSON_HYPHAE;
		public static final MaterialColor WARPED = MaterialColor.WARPED_HYPHAE;
	}
}