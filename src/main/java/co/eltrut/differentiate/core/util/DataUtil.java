package co.eltrut.differentiate.core.util;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;

public class DataUtil {

	public static void registerCompostable(ItemLike item, float compostableChance) {
		ComposterBlock.COMPOSTABLES.put(item, compostableChance);
	}
	
	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fireBlock = (FireBlock)Blocks.FIRE;
		fireBlock.setFlammable(block, encouragement, flammability);
	}
	
	public static void registerCutout(Block block, RenderType type) {
		RenderTypeLookup.setRenderLayer(block, type);
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
	
}
