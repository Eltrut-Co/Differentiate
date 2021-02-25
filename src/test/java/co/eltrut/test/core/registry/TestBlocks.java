package co.eltrut.test.core.registry;

import co.eltrut.differentiate.common.block.FollowBlock;
import co.eltrut.differentiate.common.block.VerticalSlabBlock;
import co.eltrut.differentiate.common.block.wood.PlanksBlock;
import co.eltrut.differentiate.core.registrator.BlockSubRegistrator;
import co.eltrut.differentiate.core.util.GroupUtil.Groups;
import co.eltrut.differentiate.core.util.GroupUtil.SpecialGroups;
import co.eltrut.test.common.blocks.TestSlabBlock;
import co.eltrut.test.core.Test;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestBlocks {
	
	public static final BlockSubRegistrator HELPER = Test.REGISTRATOR.getBlockSubRegistrator();
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new TestSlabBlock(Block.Properties.from(Blocks.ACACIA_WOOD)), Groups.BUILDING_BLOCKS.isImmuneToFire());
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createBlock("block_two", () -> new FollowBlock(Block.Properties.from(Blocks.DIRT), Blocks.DIRT.asItem()), Groups.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createBlock("block_three", () -> new PlanksBlock(Block.Properties.from(Blocks.DIRT)), Groups.BUILDING_BLOCKS, "differentiate");
	
	public static final RegistryObject<Block> SLABBY = HELPER.createBlock("vertical_slab", () -> new VerticalSlabBlock(Block.Properties.from(Blocks.DIRT)), SpecialGroups.VERTICAL_SLABS);
	public static final RegistryObject<Block> VERTICAL_SLAB = HELPER.createVerticalSlabBlock(Blocks.DIRT, Block.Properties.from(Blocks.DIRT));
	
}
