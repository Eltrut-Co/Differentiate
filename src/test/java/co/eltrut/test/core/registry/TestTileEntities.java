package co.eltrut.test.core.registry;

import co.eltrut.differentiate.core.registrator.BlockEntityHelper;
import co.eltrut.test.common.tileentities.TestTileEntity;
import co.eltrut.test.core.Test;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestTileEntities {

	public static final BlockEntityHelper HELPER = Test.REGISTRATOR.getHelper(ForgeRegistries.BLOCK_ENTITIES);
	
	public static final RegistryObject<BlockEntityType<TestTileEntity>> TEST = HELPER.createBlockEntity("test", TestTileEntity::new, () -> new Block[] {TestBlocks.BLOCK_TWO.get()});
	
}
