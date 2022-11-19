package co.eltrut.differentiate.platform.common.helpers;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.platform.common.utility.AttributeUtil;
import co.eltrut.differentiate.platform.common.utility.TabUtil;
import co.eltrut.differentiate.platform.common.helpers.FlammableHelper.Odds;
import co.eltrut.differentiate.platform.Helper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class BlockHelper extends Helper<Block> {
	private final Helper<Block> helper;
	private final ItemHelper itemHelper;

	public BlockHelper(Helper<Block> helper) {
		super(helper.registry, helper.modId);
		this.helper = helper;
		this.itemHelper = createItem(helper.modId);
	}

	public Supplier<Block> createBlock(String id, Supplier<Block> blockSupplier, Item.Properties props) {
		Supplier<Block> block = this.helper.register(id, blockSupplier);
		this.itemHelper.createItem(id, () -> new BlockItem(block.get(), props));

		return block;
	}

	public Supplier<Block> createBlockWithTab(String id, Supplier<Block> blockSupplier, CreativeModeTab tab, String ... modId) {
		return this.createBlock(id, blockSupplier, TabUtil.getProps(tab, modId));
	}

	public Supplier<Block> createFuelBlock(String id, Supplier<Block> blockSupplier, Item.Properties props, int length) {
		Supplier<Block> block = this.helper.register(id, blockSupplier);
		Supplier<Item> item = this.itemHelper.createItem(id, () -> new BlockItem(block.get(), props));
		FuelHelper.registerFuel(item.get(), length);
		return block;
	}

	public Supplier<Block> createFuelBlockWithTab(String id, Supplier<Block> blockSupplier, CreativeModeTab tab, int length, String ... modId) {
		return this.createFuelBlock(id, blockSupplier, TabUtil.getProps(tab, modId), length);
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String id, Supplier<Block> blockSupplier, Properties props, CreativeModeTab tab, String ... modId) {
		Supplier<Block> baseBlock = this.createBlockWithTab(id, blockSupplier, tab, modId);
		Supplier<Block> slabBlock = this.createSlabBlock(id, props, modId);
		Supplier<Block> stairsBlock = this.createStairsBlock(id, () -> new StairBlock(baseBlock.get().defaultBlockState(), props), modId);
		Supplier<Block> wallBlock = this.createWallBlock(id, props, modId);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).build();
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String id, Properties props, CreativeModeTab tab, String ... modId) {
		return this.createSimpleBlockWithVariants(id, () -> new Block(props), props, tab, modId);
	}

	public Supplier<Block> createSlabBlock(String id, Properties props, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public Supplier<Block> createStairsBlock(String id, Supplier<Block> blockSupplier, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_stairs", blockSupplier, CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public Supplier<Block> createWallBlock(String id, Properties props, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
	}

	public VariantBlocksRepo createSimpleVariants(Block base, String ... modId) {
		String id = base.getDescriptionId();
		Properties props = Properties.copy(base);
		Supplier<Block> slabBlock = this.createSlabBlock(id, props, modId);
		Supplier<Block> stairBlock = this.createStairsBlock(id, () -> new StairBlock(base.defaultBlockState(), props), modId);
		Supplier<Block> wallBlock = this.createWallBlock(id, props, modId);
		return new VariantBlocksRepo.Builder().setSlabBlock(slabBlock).setStairsBlock(stairBlock).setWallBlock(wallBlock).build();
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, String ... modId) {
		return this.createSimpleWoodVariants(woodName, color, false, modId);
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, boolean isHyphae, String ... modId) {
		String id = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		Properties props = Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD);

		// Stripped Woods
		Supplier<Block> strippedSlabBlock = this.createFuelBlockWithTab("stripped_" + id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		Supplier<Block> strippedStairBlock = this.createFuelBlockWithTab("stripped_" + id + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_OAK_WOOD.defaultBlockState(), props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		Supplier<Block> strippedWallBlock = this.createFuelBlockWithTab("stripped_" + id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, modId);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		FlammableHelper.register(strippedSlabBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammableHelper.register(strippedStairBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammableHelper.register(strippedWallBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());

		// Woods
		Supplier<Block> slabBlock = this.createFuelBlockWithTab(id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		Supplier<Block> stairBlock = this.createFuelBlockWithTab(id + "_stairs",
				() -> new StairBlock(Blocks.OAK_WOOD.defaultBlockState(), props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		Supplier<Block> wallBlock = this.createFuelBlockWithTab(id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, modId);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.build();

		StrippablesHelper.register(slabBlock.get(), strippedSlabBlock.get());
		StrippablesHelper.register(stairBlock.get(), strippedStairBlock.get());
		StrippablesHelper.register(wallBlock.get(), strippedWallBlock.get());

		FlammableHelper.register(slabBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammableHelper.register(stairBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammableHelper.register(wallBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createNetherWoodVariants(String woodName, MaterialColor color, String ... modId) {
		String id = woodName + "_hyphae";
		Properties props = Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM);

		// Stripped Woods
		Supplier<Block> strippedSlabBlock = this.createBlockWithTab("stripped_" + id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		Supplier<Block> strippedStairBlock = this.createBlockWithTab("stripped_" + id + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE.defaultBlockState(), props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		Supplier<Block> strippedWallBlock = this.createBlockWithTab("stripped_" + id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		// Woods
		Supplier<Block> slabBlock = this.createBlockWithTab(id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		Supplier<Block> stairBlock = this.createBlockWithTab(id + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE.defaultBlockState(), props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		Supplier<Block> wallBlock = this.createBlockWithTab(id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.build();

		StrippablesHelper.register(slabBlock.get(), strippedSlabBlock.get());
		StrippablesHelper.register(stairBlock.get(), strippedStairBlock.get());
		StrippablesHelper.register(wallBlock.get(), strippedWallBlock.get());

		return new WoodVariantRepo(strippedWoods, woods);
	}

	@Override
	public <E extends Block> Supplier<E> register(String key, Supplier<E> entry) {
		return helper.register(key, entry);
	}

	@Override
	public void load() {
		helper.load();
	}
}