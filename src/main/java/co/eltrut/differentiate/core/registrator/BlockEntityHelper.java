package co.eltrut.differentiate.core.registrator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityHelper extends AbstractHelper<BlockEntityType<?>> {

	public BlockEntityHelper(Registrator parent) {
		super(parent, ForgeRegistries.BLOCK_ENTITIES);
	}
	
	public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> createBlockEntity(String name, BlockEntitySupplier<? extends T> tileEntity, Supplier<Block[]> blocks) {
		return this.registry.register(name, () -> new BlockEntityType<T>(tileEntity, new HashSet<Block>(Arrays.asList(blocks.get())), null));
	}

}
