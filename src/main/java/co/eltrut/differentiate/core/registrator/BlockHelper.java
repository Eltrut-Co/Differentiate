package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.creativetab.CreativeTabSequence;
import co.eltrut.differentiate.core.util.BlockUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class BlockHelper extends AbstractHelper<Block, DeferredRegister.Blocks> {
	
	protected final ItemHelper itemRegister;
	
	public BlockHelper(Registrator parent) {
		super(parent, DeferredRegister.createBlocks(parent.getModId()));
		itemRegister = this.parent.getHelper(Registries.ITEM);
	}

	protected DeferredBlock<Block> createBlockWithoutEntry(String name, Supplier<Block> block) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createItemWithoutEntry(name,
				() -> new BlockItem(registeredBlock.get(), new Item.Properties()));

		return registeredBlock;
	}
	
	public DeferredBlock<Block> createBlock(String name, Supplier<Block> block,
	                                        ResourceKey<CreativeModeTab> tab, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createItem(name,
				() -> new BlockItem(registeredBlock.get(), new Item.Properties()), tab, mods);
		
		return registeredBlock;
	}

	public DeferredBlock<Block> createBlock(String name, Properties props,
											ResourceKey<CreativeModeTab> tab, String ...mods) {
		return this.createBlock(name, () -> new Block(props), tab, mods);
	}
	
	public DeferredBlock<Block> createFuelBlock(String name, Supplier<Block> block, int burnTime,
												ResourceKey<CreativeModeTab> tab, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createItem(name, () -> new FuelBlockItem(registeredBlock.get(),
						new Item.Properties(), burnTime),
				tab, mods);

		return registeredBlock;
	}

	public DeferredBlock<Block> createFuelBlock(String name, Properties props, int burnTime,
													  ResourceKey<CreativeModeTab> tab, String ...mods) {
		return this.createFuelBlock(name, () -> new Block(props), burnTime, tab, mods);
	}

	public DeferredBlock<Block> createFollowBlock(String name, Supplier<Block> block,
                                                  ResourceKey<CreativeModeTab> tab, ItemLike followItem, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createFollowItem(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()),
				tab, followItem, mods);

		return registeredBlock;
	}

	public DeferredBlock<Block> createFollowBlock(String name, Properties props,
												  ResourceKey<CreativeModeTab> tab, ItemLike followItem, String ...mods) {
		return this.createFollowBlock(name, () -> new Block(props), tab, followItem, mods);
	}
	
	public VariantBlocksRepo createBlockWithVariants(String name, Supplier<Block> block, Properties props,
														   ResourceKey<CreativeModeTab> tab, String ...mods) {
		String prefix = BlockUtil.getPrefix(name);

		DeferredBlock<Block> baseBlock = this.createBlock(name, block, tab, mods);
		VariantBlocksRepo repo = this.createVariantRepo(baseBlock, prefix, props);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, mods);

		return repo;
	}
	
	public VariantBlocksRepo createBlockWithVariants(String name, Properties props,
														   ResourceKey<CreativeModeTab> group, String ...mods) {
		return this.createBlockWithVariants(name, () -> new Block(props), props, group, mods);
	}

	public VariantBlocksRepo createBlockVariants(Block base, ResourceKey<CreativeModeTab> tab, String ...mods) {
		String name = BuiltInRegistries.BLOCK.getKey(base).getPath();
		String prefix = BlockUtil.getPrefix(name);

		Properties props = Properties.ofFullCopy(base);
		VariantBlocksRepo repo = this.createVariantRepo(base, prefix, props);

        List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, mods, base);

		return repo;
	}

	private VariantBlocksRepo createVariantRepo(Block base, String prefix, Properties props) {
		DeferredBlock<Block> slabBlock = this.createBlockWithoutEntry(prefix + "_slab", () -> new SlabBlock(props));
		DeferredBlock<Block> stairBlock = this.createBlockWithoutEntry(prefix + "_stairs",
				() -> new StairBlock(base.defaultBlockState(), props));
		DeferredBlock<Block> wallBlock = this.createBlockWithoutEntry(prefix + "_wall", () -> new WallBlock(props));
		DeferredBlock<Block> verticalSlabBlock = this.createBlockWithoutEntry(prefix + "_vertical_slab", () -> new VerticalSlabBlock(props));

		return new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setVerticalSlabBlock(verticalSlabBlock)
				.setWallBlock(wallBlock)
				.build();
	}

	private VariantBlocksRepo createVariantRepo(DeferredBlock<Block> base, String prefix, Properties props) {
		VariantBlocksRepo repo = this.createVariantRepo(base.get(), prefix, props);
		repo.setBlock(base);
		return repo;
	}

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
