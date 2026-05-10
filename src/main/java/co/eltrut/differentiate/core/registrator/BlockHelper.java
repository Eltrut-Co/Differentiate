package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockHelper extends AbstractHelper<Block, DeferredRegister.Blocks> {
	
	protected final ItemHelper itemRegister;
	
	public BlockHelper(Registrator parent) {
		super(parent, DeferredRegister.createBlocks(parent.getModId()));
		itemRegister = this.parent.getHelper(Registries.ITEM);
	}
	
	public DeferredBlock<Block> createBlock(String name, Supplier<Block> block, Item.Properties props) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		this.itemRegister.createItem(name, () -> new BlockItem(registeredBlock.get(), props));
		
		return registeredBlock;
	}
	
//	public DeferredBlock<Block> createSimpleBlock(String name, Supplier<Block> block, ResourceKey<CreativeModeTab> group, String ...mods) {
//		return this.createBlock(name, block, GroupUtil.getProps(group, mods));
//	}
	
	public DeferredBlock<Block> createFuelBlock(String name, Supplier<Block> block, Item.Properties props, int burnTime) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(), props, burnTime));
		return registeredBlock;
	}

//	public DeferredBlock<Block> createSimpleFuelBlock(String name, Supplier<Block> block, CreativeModeTab group, int burnTime, String ...mods) {
//		return this.createFuelBlock(name, block, GroupUtil.getProps(group, mods), burnTime);
//	}
	
//	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, CreativeModeTab group, String ...mods) {
//		DeferredBlock<Block> baseBlock = this.createSimpleBlock(name, block, group, mods);
//		DeferredBlock<Block> slabBlock = this.createSlabBlock(name, props, mods);
//		DeferredBlock<Block> stairsBlock = this.createStairsBlock(name, () -> new StairBlock(baseBlock.get().defaultBlockState(), props), mods);
//		DeferredBlock<Block> wallBlock = this.createWallBlock(name, props, mods);
//		DeferredBlock<Block> verticalSlabBlock = this.createVerticalSlabBlock(name, props, mods);
//		return new VariantBlocksRepo.Builder()
//				.setBlock(baseBlock)
//				.setSlabBlock(slabBlock)
//				.setStairsBlock(stairsBlock)
//				.setWallBlock(wallBlock)
//				.setVerticalSlabBlock(verticalSlabBlock)
//				.build();
//	}
	
//	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, CreativeModeTab group, String ...mods) {
//		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, mods);
//	}
//
//	public DeferredBlock<Block> createSlabBlock(String name, Properties props, String ...mods) {
//		String prefix = BlockUtil.getPrefix(name);
//		return this.createSimpleBlock(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
//	}
//
//	public DeferredBlock<Block> createStairsBlock(String name, Supplier<Block> block, String ...mods) {
//		String prefix = BlockUtil.getPrefix(name);
//		return this.createSimpleBlock(prefix + "_stairs", block, CreativeModeTabs.BUILDING_BLOCKS, mods);
//	}
//
//	public DeferredBlock<Block> createWallBlock(String name, Properties props, String ...mods) {
//		String prefix = BlockUtil.getPrefix(name);
//		return this.createSimpleBlock(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, mods);
//	}
//
//	public DeferredBlock<Block> createVerticalSlabBlock(String name, Properties props, String ...mods) {
//		String prefix = BlockUtil.getPrefix(name);
//		String[] modsWithQuark = CompatUtil.addQuark(mods);
//		return this.createSimpleBlock(prefix + "_vertical_slab", () -> new VerticalSlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modsWithQuark);
//	}

//	public VariantBlocksRepo createSimpleVariants(Block base, String ...mods) {
//		String name = BuiltInRegistries.BLOCK.getKey(base).getPath();
//		BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(base);
//		DeferredBlock<Block> slabBlock = this.createSlabBlock(name, props, mods);
//		DeferredBlock<Block> stairBlock = this.createStairsBlock(name, () -> new StairBlock(base::defaultBlockState, props), mods);
//		DeferredBlock<Block> wallBlock = this.createWallBlock(name, props, mods);
//		DeferredBlock<Block> verticalSlabBlock = this.createVerticalSlabBlock(name, props, mods);
//		return new VariantBlocksRepo.Builder().setSlabBlock(slabBlock).setStairsBlock(stairBlock).setWallBlock(wallBlock).setVerticalSlabBlock(verticalSlabBlock).build();
//	}

//	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, String ...mods) {
//		return this.createSimpleWoodVariants(woodName, color, false, mods);
//	}
//
//	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, boolean isHyphae, String ...mods) {
//		String name = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
//		String[] modsWithQuark = CompatUtil.addQuark(mods);
//		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD);
//
//		// Stripped Woods
//		RegistryObject<Block> strippedSlabBlock = this.createSimpleFuelBlock("stripped_" + name + "_slab",
//				() -> new LogSlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, mods);
//		RegistryObject<Block> strippedStairBlock = this.createSimpleFuelBlock("stripped_" + name + "_stairs",
//				() -> new LogStairBlock(Blocks.STRIPPED_OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, mods);
//		RegistryObject<Block> strippedWallBlock = this.createSimpleFuelBlock("stripped_" + name + "_wall",
//				() -> new LogWallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, mods);
//		RegistryObject<Block> strippedVerticalSlabBlock = this.createSimpleFuelBlock("stripped_" + name + "_vertical_slab",
//				() -> new LogVerticalSlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modsWithQuark);
//		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
//				.setSlabBlock(strippedSlabBlock)
//				.setStairsBlock(strippedStairBlock)
//				.setWallBlock(strippedWallBlock)
//				.setVerticalSlabBlock(strippedVerticalSlabBlock)
//				.build();
//
//		// Woods
//		RegistryObject<Block> slabBlock = this.createSimpleFuelBlock(name + "_slab",
//				() -> new LogSlabBlock(strippedSlabBlock, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, mods);
//		RegistryObject<Block> stairBlock = this.createSimpleFuelBlock(name + "_stairs",
//				() -> new LogStairBlock(strippedStairBlock, Blocks.OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, mods);
//		RegistryObject<Block> wallBlock = this.createSimpleFuelBlock(name + "_wall",
//				() -> new LogWallBlock(strippedWallBlock, props), CreativeModeTab.TAB_DECORATIONS, 300, mods);
//		RegistryObject<Block> verticalSlabBlock = this.createSimpleFuelBlock(name + "_vertical_slab",
//				() -> new LogVerticalSlabBlock(strippedVerticalSlabBlock, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modsWithQuark);
//		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
//				.setSlabBlock(slabBlock)
//				.setStairsBlock(stairBlock)
//				.setWallBlock(wallBlock)
//				.setVerticalSlabBlock(verticalSlabBlock)
//				.build();
//
//		return new WoodVariantRepo(strippedWoods, woods);
//	}
//
//	public WoodVariantRepo createNetherWoodVariants(String woodName, MaterialColor color, String ...mods) {
//		String name = woodName + "_hyphae";
//		String[] modsWithQuark = CompatUtil.addQuark(mods);
//		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM);
//
//		// Stripped Woods
//		RegistryObject<Block> strippedSlabBlock = this.createSimpleBlock("stripped_" + name + "_slab",
//				() -> new LogSlabBlock(props, true), CreativeModeTab.TAB_BUILDING_BLOCKS,  mods);
//		RegistryObject<Block> strippedStairBlock = this.createSimpleBlock("stripped_" + name + "_stairs",
//				() -> new LogStairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE::defaultBlockState, props, true), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
//		RegistryObject<Block> strippedWallBlock = this.createSimpleBlock("stripped_" + name + "_wall",
//				() -> new LogWallBlock(props, true), CreativeModeTab.TAB_DECORATIONS, mods);
//		RegistryObject<Block> strippedVerticalSlabBlock = this.createSimpleBlock("stripped_" + name + "_vertical_slab",
//				() -> new LogVerticalSlabBlock(props, true), CreativeModeTab.TAB_BUILDING_BLOCKS, modsWithQuark);
//		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
//				.setSlabBlock(strippedSlabBlock)
//				.setStairsBlock(strippedStairBlock)
//				.setWallBlock(strippedWallBlock)
//				.setVerticalSlabBlock(strippedVerticalSlabBlock)
//				.build();
//
//		// Woods
//		RegistryObject<Block> slabBlock = this.createSimpleBlock(name + "_slab",
//				() -> new LogSlabBlock(strippedSlabBlock, props, true), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
//		RegistryObject<Block> stairBlock = this.createSimpleBlock(name + "_stairs",
//				() -> new LogStairBlock(strippedStairBlock, Blocks.CRIMSON_HYPHAE::defaultBlockState, props, true), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
//		RegistryObject<Block> wallBlock = this.createSimpleBlock(name + "_wall",
//				() -> new LogWallBlock(strippedWallBlock, props, true), CreativeModeTab.TAB_DECORATIONS, mods);
//		RegistryObject<Block> verticalSlabBlock = this.createSimpleBlock(name + "_vertical_slab",
//				() -> new LogVerticalSlabBlock(strippedVerticalSlabBlock, props, true), CreativeModeTab.TAB_BUILDING_BLOCKS, modsWithQuark);
//		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
//				.setSlabBlock(slabBlock)
//				.setStairsBlock(stairBlock)
//				.setWallBlock(wallBlock)
//				.setVerticalSlabBlock(verticalSlabBlock)
//				.build();
//
//		return new WoodVariantRepo(strippedWoods, woods);
//	}
	
}
