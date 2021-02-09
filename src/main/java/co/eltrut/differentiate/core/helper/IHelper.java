package co.eltrut.differentiate.core.helper;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IHelper<T extends IForgeRegistryEntry<T>> {
	
	public String getModId();
	public DeferredRegister<T> getDeferredRegister();

}
