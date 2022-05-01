package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.util.AttributeUtil;
import co.eltrut.differentiate.core.util.TabUtil;
import co.eltrut.differentiate.core.util.helper.FlammablesHelper;
import co.eltrut.differentiate.core.util.helper.FlammablesHelper.Odds;
import co.eltrut.differentiate.core.util.helper.FuelsHelper;
import co.eltrut.differentiate.core.util.helper.StrippablesHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public record BlockHelper(DifferHelper<Block> blockHelper, DifferHelper<Item> itemHelper) {
	public RegistryObject<Block> createBlock(String id, Supplier<Block> blockSupplier, Item.Properties props) {
		RegistryObject<Block> block = blockHelper.register(id, blockSupplier);
		this.itemHelper.register(id, () -> new BlockItem(block.get(), props));

		return block;
	}

	public RegistryObject<Block> createBlockWithTab(String id, Supplier<Block> blockSupplier, CreativeModeTab tab, String ... modId) {
		return this.createBlock(id, blockSupplier, TabUtil.getProps(tab, modId));
	}

	public RegistryObject<Block> createFuelBlock(String id, Supplier<Block> blockSupplier, Item.Properties props, int length) {
		RegistryObject<Block> block = blockHelper.register(id, blockSupplier);
		RegistryObject<Item> item = this.itemHelper.register(id, () -> new BlockItem(block.get(), props));
		FuelsHelper.register(item.get(), length);
		return block;
	}

	public RegistryObject<Block> createFuelBlockWithTab(String id, Supplier<Block> blockSupplier, CreativeModeTab tab, int length, String ... modId) {
		return this.createFuelBlock(id, blockSupplier, TabUtil.getProps(tab, modId), length);
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String id, Supplier<Block> blockSupplier, Properties props, CreativeModeTab tab, String ... modId) {
		RegistryObject<Block> baseBlock = this.createBlockWithTab(id, blockSupplier, tab, modId);
		RegistryObject<Block> slabBlock = this.createSlabBlock(id, props, modId);
		RegistryObject<Block> stairsBlock = this.createStairsBlock(id, () -> new StairBlock(baseBlock.get()::defaultBlockState, props), modId);
		RegistryObject<Block> wallBlock = this.createWallBlock(id, props, modId);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).build();
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String id, Properties props, CreativeModeTab tab, String ... modId) {
		return this.createSimpleBlockWithVariants(id, () -> new Block(props), props, tab, modId);
	}

	public RegistryObject<Block> createSlabBlock(String id, Properties props, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public RegistryObject<Block> createStairsBlock(String id, Supplier<Block> blockSupplier, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_stairs", blockSupplier, CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public RegistryObject<Block> createWallBlock(String id, Properties props, String ... modId) {
		String prefix = AttributeUtil.getPrefix(id);
		return this.createBlockWithTab(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
	}

	public VariantBlocksRepo createSimpleVariants(Block base, String ... modId) {
		String id = base.getRegistryName().getPath();
		BlockBehaviour.Properties props = BlockBehaviour.Properties.copy(base);
		RegistryObject<Block> slabBlock = this.createSlabBlock(id, props, modId);
		RegistryObject<Block> stairBlock = this.createStairsBlock(id, () -> new StairBlock(base::defaultBlockState, props), modId);
		RegistryObject<Block> wallBlock = this.createWallBlock(id, props, modId);
		return new VariantBlocksRepo.Builder().setSlabBlock(slabBlock).setStairsBlock(stairBlock).setWallBlock(wallBlock).build();
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, String ... modId) {
		return this.createSimpleWoodVariants(woodName, color, false, modId);
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, boolean isHyphae, String ... modId) {
		String id = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createFuelBlockWithTab("stripped_" + id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		RegistryObject<Block> strippedStairBlock = this.createFuelBlockWithTab("stripped_" + id + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		RegistryObject<Block> strippedWallBlock = this.createFuelBlockWithTab("stripped_" + id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, modId);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		FlammablesHelper.register(strippedSlabBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammablesHelper.register(strippedStairBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammablesHelper.register(strippedWallBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());

		// Woods
		RegistryObject<Block> slabBlock = this.createFuelBlockWithTab(id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		RegistryObject<Block> stairBlock = this.createFuelBlockWithTab(id + "_stairs",
				() -> new StairBlock(Blocks.OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		RegistryObject<Block> wallBlock = this.createFuelBlockWithTab(id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, modId);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.build();

		StrippablesHelper.register(slabBlock.get(), strippedSlabBlock.get());
		StrippablesHelper.register(stairBlock.get(), strippedStairBlock.get());
		StrippablesHelper.register(wallBlock.get(), strippedWallBlock.get());

		FlammablesHelper.register(slabBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammablesHelper.register(stairBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());
		FlammablesHelper.register(wallBlock.get(), Odds.WOOD.getLeft(), Odds.WOOD.getRight());

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createNetherWoodVariants(String woodName, MaterialColor color, String ... modId) {
		String id = woodName + "_hyphae";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createBlockWithTab("stripped_" + id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> strippedStairBlock = this.createBlockWithTab("stripped_" + id + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> strippedWallBlock = this.createBlockWithTab("stripped_" + id + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		// Woods
		RegistryObject<Block> slabBlock = this.createBlockWithTab(id + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> stairBlock = this.createBlockWithTab(id + "_stairs",
				() -> new StairBlock(Blocks.CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> wallBlock = this.createBlockWithTab(id + "_wall",
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
}