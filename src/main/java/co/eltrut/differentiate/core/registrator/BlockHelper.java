package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.creativetab.CreativeTabSequence;
import co.eltrut.differentiate.core.util.BlockUtil;
import co.eltrut.differentiate.core.util.CompatUtil;
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
	
	protected final ItemHelper itemHelper;
	
	public BlockHelper(Registrator parent) {
		super(parent, DeferredRegister.createBlocks(parent.getModId()));
		itemHelper = this.parent.getHelper(Registries.ITEM);
	}

	protected DeferredBlock<Block> createBlockWithoutEntry(String name, Supplier<Block> block) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemHelper.createItemWithoutEntry(name,
				() -> new BlockItem(registeredBlock.get(), new Item.Properties()));

		return registeredBlock;
	}
	
	public DeferredBlock<Block> createBlock(String name, Supplier<Block> block,
	                                        ResourceKey<CreativeModeTab> tab, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemHelper.createItem(name,
				() -> new BlockItem(registeredBlock.get(), new Item.Properties()), tab, mods);
		
		return registeredBlock;
	}

	public DeferredBlock<Block> createBlock(String name, Properties props,
											ResourceKey<CreativeModeTab> tab, String ...mods) {
		return this.createBlock(name, () -> new Block(props), tab, mods);
	}

	public DeferredBlock<Block> createFollowBlock(String name, Supplier<Block> block,
                                                  ResourceKey<CreativeModeTab> tab, ItemLike followItem, String ...mods) {
		DeferredBlock<Block> registeredBlock = this.registry.register(name, block);
		DeferredItem<Item> registeredItem = this.itemHelper.createFollowItem(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()),
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
		DeferredItem<Item> registeredItem = this.itemHelper.createFollowItem(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()),
				tab, modid, followItem, mods);

		return registeredBlock;
	}
	
	public VariantBlocksRepo createBlockWithVariants(String name, Supplier<Block> block, Properties props,
														   ResourceKey<CreativeModeTab> tab, Block placeholder, String ...mods) {
		String prefix = BlockUtil.getPrefix(name);

		DeferredBlock<Block> baseBlock = this.createBlock(name, block, tab, mods);
		VariantBlocksRepo repo = this.createVariantRepo(placeholder, prefix, props).setBlock(baseBlock);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, mods);

		return repo;
	}
	
	public VariantBlocksRepo createBlockWithVariants(String name, Properties props,
														   ResourceKey<CreativeModeTab> group, Block placeholder, String ...mods) {
		return this.createBlockWithVariants(name, () -> new Block(props), props, group, placeholder, mods);
	}

	public VariantBlocksRepo createFollowBlockWithVariants(String name, Supplier<Block> block, Properties props,
														   ResourceKey<CreativeModeTab> tab, Block placeholder, Block followItem, String ...mods) {
		String prefix = BlockUtil.getPrefix(name);

		DeferredBlock<Block> baseBlock = this.createBlockWithoutEntry(name, block);
		VariantBlocksRepo repo = this.createVariantRepo(placeholder, prefix, props).setBlock(baseBlock);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, mods, followItem);

		return repo;
	}

	public VariantBlocksRepo createFollowBlockWithVariants(String name, Properties props,
														   ResourceKey<CreativeModeTab> group, Block placeholder, Block followItem, String ...mods) {
		return this.createFollowBlockWithVariants(name, () -> new Block(props), props, group, placeholder, followItem, mods);
	}

	/*
	Note that blocks from other mods can only be safely queried after registration is complete.
	This method allows for this querying to occur when building the creative tab, preventing incompatibilities with mods.
	 */
	public VariantBlocksRepo createFollowBlockWithVariants(String name, Supplier<Block> block, Properties props,
	                                              ResourceKey<CreativeModeTab> tab, Block placeholder, String modid, String followItem, String ...mods) {
		String prefix = BlockUtil.getPrefix(name);

		DeferredBlock<Block> baseBlock = this.createBlockWithoutEntry(name, block);
		VariantBlocksRepo repo = this.createVariantRepo(placeholder, prefix, props).setBlock(baseBlock);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, mods, modid, followItem);

		return repo;
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

	/*
	Note that blocks from other mods can only be safely queried after registration is complete.
	This method allows for this querying to occur when building the creative tab, preventing incompatibilities with mods.
	 */
	public VariantBlocksRepo createBlockVariants(String modid, String name, Block placeholder, Properties props, ResourceKey<CreativeModeTab> tab) {
		String prefix = BlockUtil.getPrefix(name);

		VariantBlocksRepo repo = this.createVariantRepo(placeholder, prefix, props);

		List<DeferredHolder<Block, Block>> blocks = repo.getBlocksInOrder();
		CreativeTabSequence<Block> sequence = new CreativeTabSequence<>(blocks, tab, new String[]{modid}, modid, name);

		return repo;

	}

	protected VariantBlocksRepo createVariantRepo(Block base, String prefix, Properties props) {
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

	protected VariantBlocksRepo createBlockWithVariantRepo(String name, Supplier<Block> block, Properties props,
	                                                     ResourceKey<CreativeModeTab> tab, Block placeholder, String ...mods) {
		String prefix = BlockUtil.getPrefix(name);

		DeferredBlock<Block> baseBlock = this.createBlock(name, block, tab, mods);

        return this.createVariantRepo(placeholder, prefix, props);
	}

	public WoodVariantRepo createWoodVariants(Block base, Block strippedBase, String woodName, String ...mods) {
		return this.createWoodVariants(base, strippedBase, woodName, false, mods);
	}

	public WoodVariantRepo createWoodVariants(Block base, Block strippedBase, String woodName, boolean isHyphae, String ...mods) {
		String name = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		Properties baseProps = Properties.ofFullCopy(base);
		Properties strippedProps = Properties.ofFullCopy(strippedBase);

		VariantBlocksRepo strippedWoods = this.createVariantRepo(strippedBase, "stripped_" + name, strippedProps);
		VariantBlocksRepo woods = this.createVariantRepo(base, name, baseProps);

		List<DeferredHolder<Block, Block>> baseBlocks = woods.getBlocksInOrder();
		List<DeferredHolder<Block, Block>> strippedBlocks = strippedWoods.getBlocksInOrder();

		CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, base);
		CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, mods, strippedBase);

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createWoodVariants(String modid, String base, Properties baseProps, Properties strippedProps) {
		String strippedBase = "stripped_" + base;

		VariantBlocksRepo strippedWoods = this.createVariantRepo(Blocks.STRIPPED_OAK_WOOD, strippedBase, strippedProps);
		VariantBlocksRepo woods = this.createVariantRepo(Blocks.OAK_WOOD, base, baseProps);

		List<DeferredHolder<Block, Block>> baseBlocks = woods.getBlocksInOrder();
		List<DeferredHolder<Block, Block>> strippedBlocks = strippedWoods.getBlocksInOrder();

		String[] modList = new String[]{modid};

		// NOTE: following a non-Minecraft block or item results in an exception, so I'm removing that for now
		if (modid.equals(CompatUtil.Mods.MINECRAFT)) {
			CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList, modid, base);
			CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList, modid, strippedBase);
		} else {
			CreativeTabSequence<Block> baseSequence = new CreativeTabSequence<>(baseBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList);
			CreativeTabSequence<Block> strippedSequence = new CreativeTabSequence<>(strippedBlocks, CreativeModeTabs.BUILDING_BLOCKS, modList);
		}

		return new WoodVariantRepo(strippedWoods, woods);
	}
	
}
