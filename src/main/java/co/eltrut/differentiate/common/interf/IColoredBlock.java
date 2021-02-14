package co.eltrut.differentiate.common.interf;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;

public interface IColoredBlock extends Interface {
	
	public IBlockColor getBlockColor();
	public IItemColor getItemColor();
}
