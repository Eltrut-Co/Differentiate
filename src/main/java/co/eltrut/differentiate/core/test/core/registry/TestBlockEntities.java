package co.eltrut.differentiate.core.test.core.registry;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.helper.BlockEntityHelper;
import co.eltrut.differentiate.core.test.common.blockentities.TestBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Differentiate.MOD_ID, bus = Bus.MOD)
public class TestBlockEntities {
	public static final BlockEntityHelper HELPER = Differentiate.HELPER.getHelper(ForgeRegistries.BLOCK_ENTITY_TYPES);
	
	public static final RegistryObject<BlockEntityType<?>> TEST = HELPER.createBlockEntity("test", TestBlockEntity::new, () -> new Block[] {TestBlocks.BLOCK_TWO.get()});
}