package co.eltrut.differentiate.core.util;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class DataUtil {

	public static void registerCompostable(IItemProvider item, float compostableChance) {
		ComposterBlock.COMPOSTABLES.put(item, compostableChance);
	}
	
	public static void registerFlammable(Block block, int encouragement, int flammability) {
		FireBlock fireBlock = (FireBlock)Blocks.FIRE;
		fireBlock.setFlammable(block, encouragement, flammability);
	}
	
	public static void registerCutout(Block block, RenderType type) {
		RenderTypeLookup.setRenderLayer(block, type);
	}
	
	public static void registerBlockColor(IBlockColor color, Block ...blocks) {
		Minecraft.getInstance().getBlockColors().register(color, blocks);
	}
	
	public static void registerItemColor(IItemColor color, IItemProvider ...items) {
		Minecraft.getInstance().getItemColors().register(color, items);
	}
	
	public static <T extends TileEntity> void registerTileEntityRenderer(TileEntityType<T> tileEntityType, Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>> rendererFactory) {
		ClientRegistry.bindTileEntityRenderer(tileEntityType, rendererFactory);
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
