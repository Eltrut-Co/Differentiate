package co.eltrut.differentiate.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.Property;
import net.minecraftforge.common.ToolType;

public class BlockUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BlockState transferAllBlockStates(BlockState initial, BlockState end) {
		BlockState state = end;
		
		for (Property property : initial.getProperties()) {
			state = state.with(property, initial.get(property));
		}
		return state;
	}
	
	public static class QuarkProperties {
		public static final Block.Properties SOUL_SANDSTONE = Block.Properties.create(Material.ROCK, MaterialColor.BROWN).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(0.8F);
		public static final Block.Properties BIOTITE = Block.Properties.create(Material.ROCK, MaterialColor.BLACK).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(0.8F);
		public static final Block.Properties MIDORI = Block.Properties.create(Material.ROCK, MaterialColor.LIME).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties MARBLE = Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties LIMESTONE = Block.Properties.create(Material.ROCK, MaterialColor.STONE).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties JASPER = Block.Properties.create(Material.ROCK, MaterialColor.RED_TERRACOTTA).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties SLATE = Block.Properties.create(Material.ROCK, MaterialColor.ICE).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties VOIDSTONE = Block.Properties.create(Material.ROCK, MaterialColor.BLACK).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties MYALITE = Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 6.0F);
		public static final Block.Properties ELDER_PRISMARINE = Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.5F, 10F).sound(SoundType.STONE);
	}
	
}
