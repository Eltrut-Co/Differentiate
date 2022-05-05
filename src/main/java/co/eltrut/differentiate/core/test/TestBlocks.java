package co.eltrut.differentiate.core.test;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.helper.BlockHelper;
import co.eltrut.differentiate.core.helper.DifferHelper;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Differentiate.MOD_ID, bus = Bus.MOD)
public class TestBlocks {
	public static final BlockHelper HELPER = DifferHelper.constructBlock(TestItems.HELPER.itemHelper());
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new SlabBlock(Block.Properties.copy(Blocks.ACACIA_WOOD)), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).fireResistant());
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createFuelBlockWithTab("block_two", () -> new Block(Block.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS, 300);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createBlockWithTab("block_three", () -> new Block(Block.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS, "differentiate");

	public static final RegistryObject<Block> STAIRS = HELPER.createBlockWithTab("block_three_stairs", () -> new StairBlock(BLOCK_THREE.get()::defaultBlockState, Block.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	
	public static final VariantBlocksRepo REPO = HELPER.createSimpleBlockWithVariants("dirttt", () -> new Block(Block.Properties.copy(Blocks.DIRT)), Block.Properties.copy(Blocks.DIRT), CreativeModeTab.TAB_REDSTONE, Mods.DIFFERENTIATE);
	public static final VariantBlocksRepo REEPO = HELPER.createSimpleBlockWithVariants("dirt_tiles", Block.Properties.copy(Blocks.DIRT), CreativeModeTab.TAB_BUILDING_BLOCKS);
}