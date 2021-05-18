package co.eltrut.differentiate.core.util;

import java.util.function.Function;

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
	
}
