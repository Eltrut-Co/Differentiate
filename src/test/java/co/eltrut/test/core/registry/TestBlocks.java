package co.eltrut.test.core.registry;

import co.eltrut.differentiate.common.block.DifferStairsBlock;
import co.eltrut.differentiate.common.block.FollowBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.block.wood.PlanksBlock;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.core.util.GroupUtil.Groups;
import co.eltrut.test.common.blocks.TestSlabBlock;
import co.eltrut.test.core.Test;
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
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new TestSlabBlock(Block.Properties.from(Blocks.ACACIA_WOOD)), GroupUtil.getProps(ItemGroup.BUILDING_BLOCKS).isImmuneToFire());
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createSimpleBlock("block_two", () -> new FollowBlock(Block.Properties.from(Blocks.DIRT), Blocks.DIRT.asItem()), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createSimpleBlock("block_three", () -> new PlanksBlock(Block.Properties.from(Blocks.DIRT)), ItemGroup.BUILDING_BLOCKS, "differentiate");
	
	public static final RegistryObject<Block> SLABBY = HELPER.createBlock("vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(Blocks.DIRT)), Groups.vertSlabs());
	public static final RegistryObject<Block> STAIRS = HELPER.createSimpleBlock("block_three_stairs", () -> new DifferStairsBlock(BLOCK_THREE.get()::getDefaultState, Block.Properties.from(Blocks.DIRT)), ItemGroup.BUILDING_BLOCKS);
	
}
