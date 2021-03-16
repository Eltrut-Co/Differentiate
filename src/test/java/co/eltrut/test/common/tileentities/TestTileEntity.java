package co.eltrut.test.common.tileentities;

import co.eltrut.test.core.registry.TestTileEntities;
import net.minecraft.tileentity.TileEntity;

public class TestTileEntity extends TileEntity {

	public TestTileEntity() {
		super(TestTileEntities.TEST.get());
	}

}
