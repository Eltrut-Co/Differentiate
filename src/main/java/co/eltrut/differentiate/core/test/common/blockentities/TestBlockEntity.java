package co.eltrut.differentiate.core.test.common.blockentities;

import co.eltrut.differentiate.core.test.core.registry.TestBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlockEntity extends BlockEntity {
	public TestBlockEntity(BlockPos pos, BlockState state) {
		super(TestBlockEntities.TEST.get(), pos, state);
	}
}