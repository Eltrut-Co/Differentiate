package co.eltrut.differentiate.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.util.IItemProvider;

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
	
}
