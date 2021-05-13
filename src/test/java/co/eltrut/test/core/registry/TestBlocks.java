package co.eltrut.test.core.registry;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.block.FollowBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.block.wood.PlanksBlock;
import co.eltrut.differentiate.common.repo.VariantBlocksRepo;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import co.eltrut.differentiate.core.util.CompatUtil.Mods;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.core.util.GroupUtil.Groups;
import co.eltrut.test.common.blocks.TestSlabBlock;
import co.eltrut.test.core.Test;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestBlocks {
	
	public static final BlockHelper HELPER = Test.REGISTRATOR.getHelper(ForgeRegistries.BLOCKS);
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new TestSlabBlock(AbstractBlock.Properties.copy(Blocks.ACACIA_WOOD)), GroupUtil.getProps(ItemGroup.TAB_BUILDING_BLOCKS).fireResistant());
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createSimpleBlock("block_two", () -> new FollowBlock(AbstractBlock.Properties.copy(Blocks.DIRT), Blocks.DIRT.asItem()), ItemGroup.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createSimpleBlock("block_three", () -> new PlanksBlock(AbstractBlock.Properties.copy(Blocks.DIRT)), ItemGroup.TAB_BUILDING_BLOCKS, "differentiate");
	
	public static final RegistryObject<Block> SLABBY = HELPER.createBlock("vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.DIRT)), Groups.getVerticalSlabProps());
	public static final RegistryObject<Block> STAIRS = HELPER.createSimpleBlock("block_three_stairs", () -> new DifferStairsBlock(BLOCK_THREE.get()::defaultBlockState, AbstractBlock.Properties.copy(Blocks.DIRT)), ItemGroup.TAB_BUILDING_BLOCKS);
	
	public static final VariantBlocksRepo REPO = HELPER.createSimpleBlockWithVariants("dirttt", () -> new Block(Block.Properties.copy(Blocks.DIRT)), Block.Properties.copy(Blocks.DIRT), ItemGroup.TAB_REDSTONE, Mods.DIFFERENTIATE);
	
}
