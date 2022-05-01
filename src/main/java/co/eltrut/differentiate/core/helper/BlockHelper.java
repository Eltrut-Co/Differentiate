package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.helper.FlammablesHelper.Odds;
import co.eltrut.differentiate.core.util.TabUtil;
import co.eltrut.differentiate.core.util.AttributeUtil;
import co.eltrut.differentiate.core.util.helper.FlammablesHelper;
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
	public RegistryObject<Block> createBlock(String name, Supplier<Block> block, Item.Properties props) {
		RegistryObject<Block> registeredBlock = blockHelper.register(name, block);
		this.itemHelper.register(name, () -> new BlockItem(registeredBlock.get(), props));

		return registeredBlock;
	}

	public RegistryObject<Block> createSimpleBlock(String name, Supplier<Block> block, CreativeModeTab group, String modId) {
		return this.createBlock(name, block, TabUtil.getProps(group, modId));
	}

	public RegistryObject<Block> createFuelBlock(String name, Supplier<Block> block, Item.Properties props, int burnTime) {
		RegistryObject<Block> registeredBlock = blockHelper.register(name, block);
		RegistryObject<Item> registeredItem = this.itemHelper.register(name, () -> new BlockItem(registeredBlock.get(), props));
		CompatUtil.registerFuel(registeredItem.get(), burnTime);
		return registeredBlock;
	}

	public RegistryObject<Block> createSimpleFuelBlock(String name, Supplier<Block> block, CreativeModeTab group, int burnTime, String modId) {
		return this.createFuelBlock(name, block, TabUtil.getProps(group, modId), burnTime);
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, CreativeModeTab group, String modId) {
		RegistryObject<Block> baseBlock = this.createSimpleBlock(name, block, group, modId);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, modId);
		RegistryObject<Block> stairsBlock = this.createStairsBlock(name, () -> new StairBlock(baseBlock.get()::defaultBlockState, props), modId);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, modId);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).build();
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, CreativeModeTab group, String modId) {
		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, modId);
	}

	public RegistryObject<Block> createSlabBlock(String name, Properties props, String modId) {
		String prefix = AttributeUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public RegistryObject<Block> createStairsBlock(String name, Supplier<Block> block, String modId) {
		String prefix = AttributeUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_stairs", block, CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
	}

	public RegistryObject<Block> createWallBlock(String name, Properties props, String modId) {
		String prefix = AttributeUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
	}

	public VariantBlocksRepo createSimpleVariants(Block base, String modId) {
		String name = base.getRegistryName().getPath();
		BlockBehaviour.Properties props = BlockBehaviour.Properties.copy(base);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, modId);
		RegistryObject<Block> stairBlock = this.createStairsBlock(name, () -> new StairBlock(base::defaultBlockState, props), modId);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, modId);
		return new VariantBlocksRepo.Builder().setSlabBlock(slabBlock).setStairsBlock(stairBlock).setWallBlock(wallBlock).build();
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, String modId) {
		return this.createSimpleWoodVariants(woodName, color, false, modId);
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, boolean isHyphae, String modId) {
		String name = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createSimpleFuelBlock("stripped_" + name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		RegistryObject<Block> strippedStairBlock = this.createSimpleFuelBlock("stripped_" + name + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		RegistryObject<Block> strippedWallBlock = this.createSimpleFuelBlock("stripped_" + name + "_wall",
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
		RegistryObject<Block> slabBlock = this.createSimpleFuelBlock(name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, modId);
		RegistryObject<Block> stairBlock = this.createSimpleFuelBlock(name + "_stairs",
				() -> new StairBlock(Blocks.OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, modId);
		RegistryObject<Block> wallBlock = this.createSimpleFuelBlock(name + "_wall",
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

	public WoodVariantRepo createNetherWoodVariants(String woodName, MaterialColor color, String modId) {
		String name = woodName + "_hyphae";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createSimpleBlock("stripped_" + name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> strippedStairBlock = this.createSimpleBlock("stripped_" + name + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> strippedWallBlock = this.createSimpleBlock("stripped_" + name + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, modId);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		// Woods
		RegistryObject<Block> slabBlock = this.createSimpleBlock(name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> stairBlock = this.createSimpleBlock(name + "_stairs",
				() -> new StairBlock(Blocks.CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, modId);
		RegistryObject<Block> wallBlock = this.createSimpleBlock(name + "_wall",
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