package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.block.wood.LogSlabBlock;
import co.eltrut.differentiate.common.block.wood.LogStairBlock;
import co.eltrut.differentiate.common.block.wood.LogVerticalSlabBlock;
import co.eltrut.differentiate.common.block.wood.LogWallBlock;
import co.eltrut.differentiate.common.item.FuelBlockItem;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.creativetab.CreativeTabSequence;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.DataUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
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

	protected DeferredBlock<Block> createFuelBlockWithoutEntry(String name, Supplier<Block> block, int burnTime) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createItemWithoutEntry(name,
				() -> new FuelBlockItem(registeredBlock.get(), new Item.Properties(), burnTime));

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

	/*
	Note that blocks from other mods can only be safely queried after registration is complete.
	This method allows for this querying to occur when building the creative tab, preventing incompatibilities with mods.
	 */
	public DeferredBlock<Block> createFollowBlock(String name, Supplier<Block> block,
	                                              ResourceKey<CreativeModeTab> tab, String modid, String followItem, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemRegister.createFollowItem(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()),
				tab, modid, followItem, mods);

		return registeredBlock;
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

	public VariantBlocksRepo createBlockVariants(String modid, String name, Block placeholder, Properties props, ResourceKey<CreativeModeTab> tab) {
		String prefix = BlockUtil.getPrefix(name);

		VariantBlocksRepo repo = this.createVariantRepo(placeholder, prefix, props);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, new String[]{modid}, modid, name);

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

	public WoodVariantRepo createWoodVariants(Block base, Block strippedBase, String woodName, String ...mods) {
		return this.createWoodVariants(base, strippedBase, woodName, false, mods);
	}

	public WoodVariantRepo createWoodVariants(Block base, Block strippedBase, String woodName, boolean isHyphae, String ...mods) {
		String name = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		Properties baseProps = Properties.ofFullCopy(base);
		Properties strippedProps = Properties.ofFullCopy(strippedBase);

		// Stripped Woods
		DeferredBlock<Block> strippedSlabBlock = this.createFuelBlockWithoutEntry("stripped_" + name + "_slab",
				() -> new SlabBlock(strippedProps), DataUtil.FuelTime.WOOD_SLAB);
		DeferredBlock<Block> strippedStairBlock = this.createFuelBlockWithoutEntry("stripped_" + name + "_stairs",
				() -> new StairBlock(strippedBase.defaultBlockState(), strippedProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> strippedWallBlock = this.createFuelBlockWithoutEntry("stripped_" + name + "_wall",
				() -> new WallBlock(strippedProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> strippedVerticalSlabBlock = this.createFuelBlockWithoutEntry("stripped_" + name + "_vertical_slab",
				() -> new VerticalSlabBlock(strippedProps), DataUtil.FuelTime.WOOD_SLAB);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.setVerticalSlabBlock(strippedVerticalSlabBlock)
				.build();

		// Woods
		DeferredBlock<Block> slabBlock = this.createFuelBlockWithoutEntry(name + "_slab",
				() -> new LogSlabBlock(strippedSlabBlock, baseProps), DataUtil.FuelTime.WOOD_SLAB);
		DeferredBlock<Block> stairBlock = this.createFuelBlockWithoutEntry(name + "_stairs",
				() -> new LogStairBlock(strippedStairBlock, base.defaultBlockState(), baseProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> wallBlock = this.createFuelBlockWithoutEntry(name + "_wall",
				() -> new LogWallBlock(strippedWallBlock, baseProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> verticalSlabBlock = this.createFuelBlockWithoutEntry(name + "_vertical_slab",
				() -> new LogVerticalSlabBlock(strippedVerticalSlabBlock, baseProps), DataUtil.FuelTime.WOOD_SLAB);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.setVerticalSlabBlock(verticalSlabBlock)
				.build();

		List<DeferredHolder<Block, Block>> baseBlocks = woods.getBlocksInOrder();
		List<DeferredHolder<Block, Block>> strippedBlocks = strippedWoods.getBlocksInOrder();

		CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, base);
		CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, strippedBase);

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createWoodVariants(String modid, String base, Properties baseProps, Properties strippedProps) {
		String strippedBase = "stripped_" + base;

		// Stripped Woods
		DeferredBlock<Block> strippedSlabBlock = this.createFuelBlockWithoutEntry(strippedBase + "_slab",
				() -> new SlabBlock(strippedProps), DataUtil.FuelTime.WOOD_SLAB);
		DeferredBlock<Block> strippedStairBlock = this.createFuelBlockWithoutEntry(strippedBase + "_stairs",
				() -> new StairBlock(Blocks.OAK_WOOD.defaultBlockState(), strippedProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> strippedWallBlock = this.createFuelBlockWithoutEntry(strippedBase + "_wall",
				() -> new WallBlock(strippedProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> strippedVerticalSlabBlock = this.createFuelBlockWithoutEntry(strippedBase + "_vertical_slab",
				() -> new VerticalSlabBlock(strippedProps), DataUtil.FuelTime.WOOD_SLAB);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.setVerticalSlabBlock(strippedVerticalSlabBlock)
				.build();

		// Woods
		DeferredBlock<Block> slabBlock = this.createFuelBlockWithoutEntry(base + "_slab",
				() -> new LogSlabBlock(strippedSlabBlock, baseProps), DataUtil.FuelTime.WOOD_SLAB);
		DeferredBlock<Block> stairBlock = this.createFuelBlockWithoutEntry(base + "_stairs",
				() -> new LogStairBlock(strippedStairBlock, Blocks.OAK_WOOD.defaultBlockState(), baseProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> wallBlock = this.createFuelBlockWithoutEntry(base + "_wall",
				() -> new LogWallBlock(strippedWallBlock, baseProps), DataUtil.FuelTime.WOOD_BLOCK);
		DeferredBlock<Block> verticalSlabBlock = this.createFuelBlockWithoutEntry(base + "_vertical_slab",
				() -> new LogVerticalSlabBlock(strippedVerticalSlabBlock, baseProps), DataUtil.FuelTime.WOOD_SLAB);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.setVerticalSlabBlock(verticalSlabBlock)
				.build();

		List<DeferredHolder<Block, Block>> baseBlocks = woods.getBlocksInOrder();
		List<DeferredHolder<Block, Block>> strippedBlocks = strippedWoods.getBlocksInOrder();

		String[] modList = new String[]{modid};
		CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList, modid, base);
		CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList, modid, strippedBase);

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createNetherWoodVariants(Block base, Block strippedBase, String woodName, Properties props, String ...mods) {
		String name = woodName + "_hyphae";
		Properties baseProps = Properties.ofFullCopy(base);
		Properties strippedProps = Properties.ofFullCopy(strippedBase);

		// Stripped Woods
		DeferredBlock<Block> strippedSlabBlock = this.createBlockWithoutEntry("stripped_" + name + "_slab",
				() -> new SlabBlock(strippedProps));
		DeferredBlock<Block> strippedStairBlock = this.createBlockWithoutEntry("stripped_" + name + "_stairs",
				() -> new StairBlock(strippedBase.defaultBlockState(), strippedProps));
		DeferredBlock<Block> strippedWallBlock = this.createBlockWithoutEntry("stripped_" + name + "_wall",
				() -> new WallBlock(strippedProps));
		DeferredBlock<Block> strippedVerticalSlabBlock = this.createBlockWithoutEntry("stripped_" + name + "_vertical_slab",
				() -> new VerticalSlabBlock(strippedProps));
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.setVerticalSlabBlock(strippedVerticalSlabBlock)
				.build();

		// Woods
		DeferredBlock<Block> slabBlock = this.createBlockWithoutEntry(name + "_slab",
				() -> new LogSlabBlock(strippedSlabBlock, baseProps));
		DeferredBlock<Block> stairBlock = this.createBlockWithoutEntry(name + "_stairs",
				() -> new LogStairBlock(strippedStairBlock, base.defaultBlockState(), baseProps));
		DeferredBlock<Block> wallBlock = this.createBlockWithoutEntry(name + "_wall",
				() -> new LogWallBlock(strippedWallBlock, baseProps));
		DeferredBlock<Block> verticalSlabBlock = this.createBlockWithoutEntry(name + "_vertical_slab",
				() -> new LogVerticalSlabBlock(strippedVerticalSlabBlock, baseProps));
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.setVerticalSlabBlock(verticalSlabBlock)
				.build();

		List<DeferredHolder<Block, Block>> baseBlocks = woods.getBlocksInOrder();
		List<DeferredHolder<Block, Block>> strippedBlocks = strippedWoods.getBlocksInOrder();

		CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, base);
		CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, strippedBase);

		return new WoodVariantRepo(strippedWoods, woods);

	}
	
}
