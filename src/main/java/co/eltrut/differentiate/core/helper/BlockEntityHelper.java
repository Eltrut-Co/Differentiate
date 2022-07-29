package co.eltrut.differentiate.core.helper;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockEntityHelper extends AbstractHelper<BlockEntityType<?>> {

	private final DifferHelper parent;

	public BlockEntityHelper(DifferHelper parent) {
		super(parent, ForgeRegistries.BLOCK_ENTITY_TYPES);
		this.parent = parent;

	}

	public RegistryObject<BlockEntityType<?>> createBlockEntity(String id, BlockEntitySupplier<?> blockEntity, Supplier<Block[]> blocks) {
		return this.deferredRegister.register(id, () -> BlockEntityType.Builder.of(blockEntity, blocks.get()).build(null));
	}
}