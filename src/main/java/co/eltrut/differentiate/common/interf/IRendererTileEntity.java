package co.eltrut.differentiate.common.interf;

import com.google.common.base.Function;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public interface IRendererTileEntity<T extends TileEntity> extends Interface {
	
	public Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>> getRendererFactory();

}
