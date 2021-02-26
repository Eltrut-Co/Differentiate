package co.eltrut.differentiate.core.registrator;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IHelper<T extends IForgeRegistryEntry<T>> {
	
	public Registrator getParent();
	public DeferredRegister<T> getDeferredRegister();
	public void register(IEventBus bus);

}
