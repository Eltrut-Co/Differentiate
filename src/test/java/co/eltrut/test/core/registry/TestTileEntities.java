package co.eltrut.test.core.registry;

import co.eltrut.differentiate.core.helper.BlockEntityHelper;
import co.eltrut.differentiate.core.helper.DifferHelper;
import co.eltrut.test.common.tileentities.TestTileEntity;
import co.eltrut.test.core.Test;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestTileEntities {
	public static final BlockEntityHelper HELPER = DifferHelper.constructBlockEntity(Test.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<?>> TEST = HELPER.createBlockEntity("test", TestTileEntity::new, () -> new Block[] {TestBlocks.BLOCK_TWO.get()});
}