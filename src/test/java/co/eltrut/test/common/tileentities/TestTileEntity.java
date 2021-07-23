package co.eltrut.test.common.tileentities;

import co.eltrut.test.core.registry.TestTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestTileEntity extends BlockEntity {

	public TestTileEntity(BlockPos pos, BlockState state) {
		super(TestTileEntities.TEST.get(), pos, state);
	}

}
