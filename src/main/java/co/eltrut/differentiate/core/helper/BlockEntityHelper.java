package co.eltrut.differentiate.core.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.RegistryObject;

public record BlockEntityHelper(DifferHelper<BlockEntityType<?>> blockEntityHelper) {
	public <T extends BlockEntity> RegistryObject<BlockEntityType<?>> createBlockEntity(String name, BlockEntitySupplier<? extends T> tileEntity, Supplier<Block[]> blocks) {
		return blockEntityHelper.register(name, () -> new BlockEntityType<T>(tileEntity, new HashSet<>(Arrays.asList(blocks.get())), null));
	}
}