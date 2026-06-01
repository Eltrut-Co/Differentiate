package co.eltrut.differentiate.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MapColor;

public class BlockUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static BlockState transferAllBlockStates(BlockState initial, BlockState end) {
		BlockState state = end;
		
		for (Property property : initial.getProperties()) {
			if (end.hasProperty(property)) {
                state = state.setValue(property, initial.getValue(property));
            }
		}
		return state;
	}
	
	public static void registerDispenserBehavior(Item item, Block block, DispenseItemBehavior newBehavior) {
		DispenseItemBehavior oldBehavior = DispenserBlock.DISPENSER_REGISTRY.get(item);
		DispenserBlock.registerBehavior(item, (source, stack) -> {
			Direction dir = source.state().getValue(DispenserBlock.FACING);
			BlockPos pos = source.pos().relative(dir);
			BlockState state = source.level().getBlockState(pos);

			return state.is(block) ? newBehavior.dispense(source, stack) : oldBehavior.dispense(source, stack);
		});
	}

	public static Block getBlockFromId(String namespace, String path) {
		if (BuiltInRegistries.BLOCK.containsKey(ResourceLocation.fromNamespaceAndPath(namespace, path))) {
			return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(namespace, path));
		}
		return null;
	}

	public static String getIdFromBlock(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	public static String getPrefix(String name) {
		return name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
	}
	
	public static class QuarkProperties {

		// STONES
		public static final Block.Properties SOUL_SANDSTONE = OldProperties.stone().mapColor(MapColor.COLOR_BROWN)
				.requiresCorrectToolForDrops()
				.strength(0.8F);
		public static final Block.Properties MIDORI = OldProperties.stone().mapColor(MapColor.COLOR_LIGHT_GREEN)
				.requiresCorrectToolForDrops()
				.strength(1.5F, 6.0F);
		public static final Block.Properties LIMESTONE = OldProperties.stone().mapColor(MapColor.STONE)
				.requiresCorrectToolForDrops()
				.strength(1.5F, 6.0F);
		public static final Block.Properties JASPER = OldProperties.stone().mapColor(MapColor.TERRACOTTA_RED)
				.requiresCorrectToolForDrops()
				.strength(1.5F, 6.0F);
		public static final Block.Properties SHALE = OldProperties.stone().mapColor(MapColor.ICE)
				.requiresCorrectToolForDrops()
				.strength(1.5F, 6.0F);
		public static final Block.Properties MYALITE = OldProperties.stone().mapColor(MapColor.COLOR_PURPLE)
				.requiresCorrectToolForDrops()
				.strength(1.5F, 6.0F);

		// WOODS
		public static final Block.Properties ANCIENT = OldProperties.wood().mapColor(MapColor.TERRACOTTA_WHITE);
		public static final Block.Properties AZALEA = OldProperties.wood().mapColor(MapColor.COLOR_LIGHT_GREEN);
		public static final Block.Properties STRIPPED_AZALEA = OldProperties.wood().mapColor(MapColor.COLOR_BROWN);
		public static final Block.Properties BLOSSOM = OldProperties.wood().mapColor(MapColor.COLOR_RED);
		public static final Block.Properties STRIPPED_BLOSSOM = OldProperties.wood().mapColor(MapColor.COLOR_BROWN);

	}

	public static class AbnormalsProperties {

	}

	public static class OldProperties {
		// source: https://gist.github.com/GizmoTheMoonPig/77a90a48e0aeecd15b4c524e1c7f0a4a

		public static BlockBehaviour.Properties stone() {
			return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM);
		}

		public static BlockBehaviour.Properties wood() {
			return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD);
		}

		public static BlockBehaviour.Properties wool() {
			return BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).ignitedByLava().sound(SoundType.WOOL);
		}

	}
	
}
