package co.eltrut.test.common.blocks;

import co.eltrut.differentiate.common.interf.ICompostableItem;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SlabBlock;

public class TestSlabBlock extends SlabBlock implements ICompostableItem, IRenderTypeBlock {

	public TestSlabBlock(Properties properties) {
		super(properties);
	}

	@Override
	public float getCompostableChance() {
		return 0.8F;
	}

	@Override
	public RenderType getRenderType() {
		return RenderType.translucent();
	}

}
