package co.eltrut.differentiate.core.util;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.apache.commons.lang3.tuple.Pair;

public class DataUtil {
	
	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fireBlock = (FireBlock)Blocks.FIRE;
		fireBlock.setFlammable(block, encouragement, flammability);
	}

	public static void registerBlockColor(RegisterColorHandlersEvent.Block event, BlockColor color, Block ...blocks) {
		event.register(color, blocks);
	}

	public static void registerItemColor(RegisterColorHandlersEvent.Item event, ItemColor color, Block ...blocks) {
		event.register(color, blocks);
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
