package co.eltrut.differentiate.core.registrator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityHelper extends AbstractHelper<TileEntityType<?>> {

	public TileEntityHelper(Registrator parent) {
		super(parent, ForgeRegistries.TILE_ENTITIES);
	}
	
	public <T extends TileEntity> RegistryObject<TileEntityType<T>> createTileEntity(String name, Supplier<? extends T> tileEntity, Block ...blocks) {
		return this.registry.register(name, () -> new TileEntityType<T>(tileEntity, new HashSet<Block>(Arrays.asList(blocks)), null));
	}

}
