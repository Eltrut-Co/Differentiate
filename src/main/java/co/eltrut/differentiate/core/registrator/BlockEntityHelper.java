package co.eltrut.differentiate.core.registrator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityHelper extends AbstractHelper<BlockEntityType<?>, DeferredRegister<BlockEntityType<?>>> {

	public BlockEntityHelper(Registrator parent) {
		super(parent, DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, parent.getModId()));
	}
	
//	public <T extends BlockEntity> DeferredHolder<BlockEntityType<T>> createBlockEntity(String name, BlockEntitySupplier<? extends T> tileEntity, Supplier<Block[]> blocks) {
//		return this.registry.register(name, () -> new BlockEntityType<T>(tileEntity, new HashSet<Block>(Arrays.asList(blocks.get())), null));
//	}

}
