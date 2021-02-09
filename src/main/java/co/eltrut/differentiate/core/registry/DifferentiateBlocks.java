package co.eltrut.differentiate.core.registry;

import co.eltrut.differentiate.common.blocks.base.DifferSlabBlock;
import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.helper.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Differentiate.MOD_ID, bus = Bus.MOD)
public class DifferentiateBlocks {
	
	public static final BlockHelper HELPER = Differentiate.REGISTRATOR.getBlockHelper();
	
	public static final RegistryObject<Block> BLOCK = HELPER.block("block", () -> new DifferSlabBlock(Block.Properties.from(Blocks.ACACIA_WOOD)));
	
}