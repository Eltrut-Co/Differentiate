package co.eltrut.differentiate.core.registry;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.helper.BlockHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Differentiate.MOD_ID, bus = Bus.MOD)
public class DifferentiateBlocks {
	
	public static final BlockHelper HELPER = Differentiate.REGISTRATOR.getBlockHelper();
	
	
}