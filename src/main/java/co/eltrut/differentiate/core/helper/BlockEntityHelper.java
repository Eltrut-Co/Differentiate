package co.eltrut.differentiate.core.helper;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public record BlockEntityHelper(DifferHelper<BlockEntityType<?>> blockEntityHelper) {
	public RegistryObject<BlockEntityType<?>> createBlockEntity(String id, BlockEntitySupplier<?> blockEntity, Supplier<Block[]> blocks) {
		return blockEntityHelper.register(id, () -> BlockEntityType.Builder.of(blockEntity, blocks.get()).build(null));
	}
}