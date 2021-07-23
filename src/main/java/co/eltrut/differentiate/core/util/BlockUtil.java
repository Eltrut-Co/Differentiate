package co.eltrut.differentiate.core.util;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class BlockUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BlockState transferAllBlockStates(BlockState initial, BlockState end) {
		BlockState state = end;
		
		for (Property property : initial.getProperties()) {
			state = state.setValue(property, initial.getValue(property));
		}
		return state;
	}
	
	public static void registerDispenserBehavior(Item item, Block block, DispenseItemBehavior newBehavior) {
		DispenseItemBehavior oldBehavior = DispenserBlock.DISPENSER_REGISTRY.get(item);
		DispenserBlock.registerBehavior(item, (source, stack) -> {
			Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
			BlockPos pos = source.getPos().relative(dir);
			BlockState state = source.getLevel().getBlockState(pos);
			
			return state.is(block) ? newBehavior.dispense(source, stack) : oldBehavior.dispense(source, stack);
		});
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IForgeRegistryEntry<T>> T[] toArray(List<RegistryObject<T>> list) {
		return (T[])list.stream().map(RegistryObject::get).toArray();
	}
	
	public static class QuarkProperties {
		
		public static final Block.Properties SOUL_SANDSTONE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.8F);
		public static final Block.Properties BIOTITE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.8F);
		public static final Block.Properties MIDORI = Block.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties MARBLE = Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties LIMESTONE = Block.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties JASPER = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties SLATE = Block.Properties.of(Material.STONE, MaterialColor.ICE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties VOIDSTONE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties MYALITE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F);
		public static final Block.Properties ELDER_PRISMARINE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 10F).sound(SoundType.STONE);
		
	}
	
}
