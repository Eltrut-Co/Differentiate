package co.eltrut.test.core.registry;

import co.eltrut.differentiate.common.block.FollowBlock;
import co.eltrut.differentiate.core.registrator.sub.BlockSubRegistrator;
import co.eltrut.test.common.blocks.TestSlabBlock;
import co.eltrut.test.core.Test;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestBlocks {
	
	public static final BlockSubRegistrator HELPER = Test.REGISTRATOR.getBlockSubRegistrator();
	
	public static final RegistryObject<Block> BLOCK = HELPER.createBlock("block", () -> new TestSlabBlock(Block.Properties.from(Blocks.ACACIA_WOOD)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_TWO = HELPER.createBlock("block_two", () -> new FollowBlock(Block.Properties.from(Blocks.DIRT), Blocks.DIRT.asItem()), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BLOCK_THREE = HELPER.createBlock("block_three", () -> new FollowBlock(Block.Properties.from(Blocks.DIRT), Blocks.DIRT.asItem()), ItemGroup.BUILDING_BLOCKS, "differentiate");
	
}
