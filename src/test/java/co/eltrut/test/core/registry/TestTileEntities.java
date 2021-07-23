package co.eltrut.test.core.registry;

import co.eltrut.differentiate.core.registrator.BlockEntityHelper;
import co.eltrut.test.common.tileentities.TestTileEntity;
import co.eltrut.test.core.Test;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestTileEntities {

	public static final BlockEntityHelper HELPER = Test.REGISTRATOR.getHelper(ForgeRegistries.TILE_ENTITIES);
	
	public static final RegistryObject<TileEntityType<TestTileEntity>> TEST = HELPER.createTileEntity("test", TestTileEntity::new, () -> new Block[] {TestBlocks.BLOCK_TWO.get()});
	
}
