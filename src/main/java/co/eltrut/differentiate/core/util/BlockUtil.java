package co.eltrut.differentiate.core.util;

import net.minecraft.block.AbstractBlock;
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
			state = state.setValue(property, initial.getValue(property));
		}
		return state;
	}
	
	public static class QuarkProperties {
		public static final AbstractBlock.Properties SOUL_SANDSTONE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.8F);
		public static final AbstractBlock.Properties BIOTITE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.8F);
		public static final AbstractBlock.Properties MIDORI = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties MARBLE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties LIMESTONE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties JASPER = AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties SLATE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.ICE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties VOIDSTONE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties MYALITE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final AbstractBlock.Properties ELDER_PRISMARINE = AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 10F).sound(SoundType.STONE);
	}
	
}
