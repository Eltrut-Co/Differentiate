package co.eltrut.differentiate.common.blocks.base;

import co.eltrut.differentiate.common.blocks.interf.ICompostableItem;
import co.eltrut.differentiate.common.blocks.interf.IRenderTypeBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.renderer.RenderType;

public class DifferSlabBlock extends SlabBlock implements ICompostableItem, IRenderTypeBlock {

	public DifferSlabBlock(Properties properties) {
		super(properties);
	}

	@Override
	public float getCompostableChance() {
		return 0.8F;
	}

	@Override
	public RenderType getRenderType() {
		return RenderType.getTranslucent();
	}

}
