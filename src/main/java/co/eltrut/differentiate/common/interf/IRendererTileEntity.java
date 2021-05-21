package co.eltrut.differentiate.common.interf;

import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public interface IRendererTileEntity<T extends TileEntity> extends Interface {
	
	public Supplier<Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>>> getRendererFactory();

}
