package co.eltrut.test.core.registry;

import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import co.eltrut.differentiate.core.registrator.DifferHelper;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import co.eltrut.differentiate.core.util.CreativeTabUtil;
import co.eltrut.differentiate.core.util.CreativeTabUtil.Groups;
import co.eltrut.test.common.blocks.TestSlabBlock;
import co.eltrut.test.core.Test;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestBlocks {

	public static final DifferHelper<Block> HELPER = DifferHelper.create(ForgeRegistries.BLOCKS, Test.MOD_ID);
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new TestSlabBlock(Block.Properties.copy(Blocks.ACACIA_WOOD)), CreativeTabUtil.getProps(CreativeModeTab.TAB_BUILDING_BLOCKS).fireResistant());
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createSimpleBlock("block_two", () -> new FollowBlock(Block.Properties.copy(Blocks.DIRT), Blocks.DIRT.asItem()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createSimpleBlock("block_three", () -> new PlanksBlock(Block.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS, "differentiate");
	
	public static final RegistryObject<Block> SLABBY = HELPER.createBlock("vertical_slab", () -> new VerticalSlabBlock(Block.Properties.copy(Blocks.DIRT)), Groups.getVerticalSlabProps());
	public static final RegistryObject<Block> STAIRS = HELPER.createSimpleBlock("block_three_stairs", () -> new StairBlock(BLOCK_THREE.get()::defaultBlockState, Block.Properties.copy(Blocks.DIRT)), CreativeModeTab.TAB_BUILDING_BLOCKS);
	
	public static final VariantBlocksRepo REPO = HELPER.createSimpleBlockWithVariants("dirttt", () -> new Block(Block.Properties.copy(Blocks.DIRT)), Block.Properties.copy(Blocks.DIRT), CreativeModeTab.TAB_REDSTONE, Mods.DIFFERENTIATE);
	public static final VariantBlocksRepo REEPO = HELPER.createSimpleBlockWithVariants("dirt_tiles", Block.Properties.copy(Blocks.DIRT), CreativeModeTab.TAB_BUILDING_BLOCKS);
	
}
