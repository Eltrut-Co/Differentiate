package co.eltrut.differentiate.platform.common.helpers;

import co.eltrut.differentiate.platform.Helper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

import java.util.function.Supplier;

public class BlockEntityHelper extends Helper<BlockEntityType<?>> {
	private final Helper<BlockEntityType<?>> helper;

	public BlockEntityHelper(Helper<BlockEntityType<?>> helper) {
		super(helper.registry, helper.modId);
		this.helper = helper;

	}

	public Supplier<BlockEntityType<?>> createBlockEntity(String id, BlockEntitySupplier<?> blockEntity, Supplier<Block[]> blocks) {
		return this.helper.register(id, () -> BlockEntityType.Builder.of(blockEntity, blocks.get()).build(null));
	}

	@Override
	public <E extends BlockEntityType<?>> Supplier<E> register(String key, Supplier<E> entry) {
		return helper.register(key, entry);
	}

	@Override
	public void load() {
		helper.load();
	}
}