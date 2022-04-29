package co.eltrut.differentiate.core.util;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class CompatUtil {
	public static void registerCompostable(ItemLike item, float compostableChance) {
		ComposterBlock.add(item, compostableChance);
	}

	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fireBlock = (FireBlock) Blocks.FIRE;
		fireBlock.setFlammable(block, encouragement, flammability);
	}

	public static void registerFuel(ItemLike item, int duration) {
		Map<Item, Integer> map = Maps.newLinkedHashMap();
		AbstractFurnaceBlockEntity.add(map, item, duration);
	}

	public static void registerCutout(Block block, RenderType type) {
		ItemBlockRenderTypes.setRenderLayer(block, type);
	}

	public static void registerBlockColor(BlockColor color, Block ...blocks) {
		Minecraft.getInstance().getBlockColors().register(color, blocks);
	}

	public static void registerItemColor(ItemColor color, ItemLike ...items) {
		Minecraft.getInstance().getItemColors().register(color, items);
	}

	public static class CompostableChance {
		public static final float SEEDS = 0.3F;
		public static final float PLANTS = 0.65F;
		public static final float BAKED_GOODS = 0.85F;
		public static final float PIES = 1.0F;
	}

	public static class FlammableChance {
		public static final Pair<Integer, Integer> WOOD = Pair.of(5, 5);
		public static final Pair<Integer, Integer> PLANKS = Pair.of(5, 20);
		public static final Pair<Integer, Integer> BOOKSHELF = Pair.of(30, 20);
		public static final Pair<Integer, Integer> LEAVES = Pair.of(30, 60);
		public static final Pair<Integer, Integer> WOOL = Pair.of(30, 60);
		public static final Pair<Integer, Integer> CARPET = Pair.of(60, 20);
		public static final Pair<Integer, Integer> FLOWER = Pair.of(60, 100);
	}


	public static class Mods {
		// Core
		public static final String MINECRAFT = "minecraft";
		public static final String FORGE = "forge";
		
		// Vazkii
		public static final String AUTOREGLIB = "autoreglib";
		public static final String QUARK = "quark";
		
		// Abnormals
		public static final String ABNORMALS_DELIGHT = "abnormals_delight";
		public static final String ALLUREMENT = "allurement";
		public static final String ATMOSPHERIC = "atmospheric";
		public static final String AUTUMNITY = "autumnity";
		public static final String BAMBOO_BLOCKS = "bamboo_blocks";
		public static final String BERRY_GOOD = "berry_good";
		public static final String BLUEPRINT = "blueprint";
		public static final String BUZZIER_BEES = "buzzier_bees";
		public static final String CAVERNS_AND_CHASMS = "caverns_and_chasms";
		public static final String ENDERGETIC_EXPANSION = "endergetic";
		public static final String ENVIRONMENTAL = "environmental";
		public static final String EXTRA_BOATS = "extraboats";
		public static final String NEAPOLITAN = "neapolitan";
		public static final String NETHER_EXTENSION = "nether_extension";
		public static final String PERSONALITY = "personality";
		public static final String SAVAGE_AND_RAVAGE = "savageandravage";
		public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
		
		// Aurora
		public static final String ABUNDANCE = "abundance";
		public static final String BAYOU_BLUES = "bayou_blues";
		public static final String BETTER_BADLANDS = "better_badlands";
		public static final String ENHANCED_MUSHROOMS = "enhanced_mushrooms";
		public static final String FRUITFUL	= "fruitful";
		public static final String REFORESTED = "reforested";
		
		// Evoslab
		public static final String ASSEMBLY = "assembly";
		public static final String BREADCRUMBS = "breadcrumbs";
		public static final String COOKIELICIOUS = "cookielicious";
		
		// Eltrut & Co.
		public static final String ADDENDUM = "addendum";
		public static final String DIFFERENTIATE = "differentiate";
		public static final String FLAMBOYANT = "flamboyant";
		public static final String LEPTON = "lepton";
		public static final String MORE_RESPAWN_ANCHORS = "morerespawnanchors";
		public static final String TOTALLY_WILD = "totally_wild";
		
		// Other
		public static final String CREATE = "create";
		public static final String CRUMBS = "crumbs";
		public static final String FARMERS_DELIGHT = "farmersdelight";
		public static final String INFERNAL_EXPANSION = "infernalexp";
		public static final String OUTER_END = "outer_end";
	}
}