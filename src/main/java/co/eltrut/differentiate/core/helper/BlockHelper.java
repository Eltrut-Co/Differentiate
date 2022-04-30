package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.common.repo.WoodVariantRepo;
import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.CompatUtil.FlammableChance;
import co.eltrut.differentiate.core.util.CreativeTabUtil;
import co.eltrut.differentiate.core.util.PropertyUtil;
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

	public RegistryObject<Block> createSimpleBlock(String name, Supplier<Block> block, CreativeModeTab group, String... mods) {
		return this.createBlock(name, block, CreativeTabUtil.getProps(group, mods));
	}

	public RegistryObject<Block> createFuelBlock(String name, Supplier<Block> block, Item.Properties props, int burnTime) {
		RegistryObject<Block> registeredBlock = blockHelper.register(name, block);
		RegistryObject<Item> registeredItem = this.itemHelper.register(name, () -> new BlockItem(registeredBlock.get(), props));
		CompatUtil.registerFuel(registeredItem.get(), burnTime);
		return registeredBlock;
	}

	public RegistryObject<Block> createSimpleFuelBlock(String name, Supplier<Block> block, CreativeModeTab group, int burnTime, String... mods) {
		return this.createFuelBlock(name, block, CreativeTabUtil.getProps(group, mods), burnTime);
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Supplier<Block> block, Properties props, CreativeModeTab group, String... mods) {
		RegistryObject<Block> baseBlock = this.createSimpleBlock(name, block, group, mods);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, mods);
		RegistryObject<Block> stairsBlock = this.createStairsBlock(name, () -> new StairBlock(baseBlock.get()::defaultBlockState, props), mods);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, mods);
		return new VariantBlocksRepo.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).build();
	}

	public VariantBlocksRepo createSimpleBlockWithVariants(String name, Properties props, CreativeModeTab group, String... mods) {
		return this.createSimpleBlockWithVariants(name, () -> new Block(props), props, group, mods);
	}

	public RegistryObject<Block> createSlabBlock(String name, Properties props, String... mods) {
		String prefix = PropertyUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_slab", () -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
	}

	public RegistryObject<Block> createStairsBlock(String name, Supplier<Block> block, String... mods) {
		String prefix = PropertyUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_stairs", block, CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
	}

	public RegistryObject<Block> createWallBlock(String name, Properties props, String... mods) {
		String prefix = PropertyUtil.getPrefix(name);
		return this.createSimpleBlock(prefix + "_wall", () -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, mods);
	}

	public VariantBlocksRepo createSimpleVariants(Block base, String... mods) {
		String name = base.getRegistryName().getPath();
		BlockBehaviour.Properties props = BlockBehaviour.Properties.copy(base);
		RegistryObject<Block> slabBlock = this.createSlabBlock(name, props, mods);
		RegistryObject<Block> stairBlock = this.createStairsBlock(name, () -> new StairBlock(base::defaultBlockState, props), mods);
		RegistryObject<Block> wallBlock = this.createWallBlock(name, props, mods);
		return new VariantBlocksRepo.Builder().setSlabBlock(slabBlock).setStairsBlock(stairBlock).setWallBlock(wallBlock).build();
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, String... mods) {
		return this.createSimpleWoodVariants(woodName, color, false, mods);
	}

	public WoodVariantRepo createSimpleWoodVariants(String woodName, MaterialColor color, boolean isHyphae, String... mods) {
		String name = isHyphae ? woodName + "_hyphae" : woodName + "_wood";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createSimpleFuelBlock("stripped_" + name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, mods);
		RegistryObject<Block> strippedStairBlock = this.createSimpleFuelBlock("stripped_" + name + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, mods);
		RegistryObject<Block> strippedWallBlock = this.createSimpleFuelBlock("stripped_" + name + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, mods);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		CompatUtil.registerFlammable(strippedSlabBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());
		CompatUtil.registerFlammable(strippedStairBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());
		CompatUtil.registerFlammable(strippedWallBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());

		// Woods
		RegistryObject<Block> slabBlock = this.createSimpleFuelBlock(name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, 150, mods);
		RegistryObject<Block> stairBlock = this.createSimpleFuelBlock(name + "_stairs",
				() -> new StairBlock(Blocks.OAK_WOOD::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, 300, mods);
		RegistryObject<Block> wallBlock = this.createSimpleFuelBlock(name + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, 300, mods);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.build();

		CompatUtil.registerStrippables(slabBlock.get(), strippedSlabBlock.get());
		CompatUtil.registerStrippables(stairBlock.get(), strippedStairBlock.get());
		CompatUtil.registerStrippables(wallBlock.get(), strippedWallBlock.get());

		CompatUtil.registerFlammable(slabBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());
		CompatUtil.registerFlammable(stairBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());
		CompatUtil.registerFlammable(wallBlock.get(), FlammableChance.WOOD.getLeft(), FlammableChance.WOOD.getRight());

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public WoodVariantRepo createNetherWoodVariants(String woodName, MaterialColor color, String... mods) {
		String name = woodName + "_hyphae";
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.NETHER_WOOD, color).strength(2.0F).sound(SoundType.STEM);

		// Stripped Woods
		RegistryObject<Block> strippedSlabBlock = this.createSimpleBlock("stripped_" + name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> strippedStairBlock = this.createSimpleBlock("stripped_" + name + "_stairs",
				() -> new StairBlock(Blocks.STRIPPED_CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> strippedWallBlock = this.createSimpleBlock("stripped_" + name + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, mods);
		VariantBlocksRepo strippedWoods = new VariantBlocksRepo.Builder()
				.setSlabBlock(strippedSlabBlock)
				.setStairsBlock(strippedStairBlock)
				.setWallBlock(strippedWallBlock)
				.build();

		// Woods
		RegistryObject<Block> slabBlock = this.createSimpleBlock(name + "_slab",
				() -> new SlabBlock(props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> stairBlock = this.createSimpleBlock(name + "_stairs",
				() -> new StairBlock(Blocks.CRIMSON_HYPHAE::defaultBlockState, props), CreativeModeTab.TAB_BUILDING_BLOCKS, mods);
		RegistryObject<Block> wallBlock = this.createSimpleBlock(name + "_wall",
				() -> new WallBlock(props), CreativeModeTab.TAB_DECORATIONS, mods);
		VariantBlocksRepo woods = new VariantBlocksRepo.Builder()
				.setSlabBlock(slabBlock)
				.setStairsBlock(stairBlock)
				.setWallBlock(wallBlock)
				.build();

		CompatUtil.registerStrippables(slabBlock.get(), strippedSlabBlock.get());
		CompatUtil.registerStrippables(stairBlock.get(), strippedStairBlock.get());
		CompatUtil.registerStrippables(wallBlock.get(), strippedWallBlock.get());

		return new WoodVariantRepo(strippedWoods, woods);
	}

	public DifferHelper<Block> getHelper() {
		return this.blockHelper;
	}
}