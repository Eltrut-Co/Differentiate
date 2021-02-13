package co.eltrut.differentiate.core.registrator.sub;

import co.eltrut.differentiate.core.registrator.Registrator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface ISubRegistrator<T extends IForgeRegistryEntry<T>> {
	
	public Registrator getParent();
	public DeferredRegister<T> getDeferredRegister();
	public void register(IEventBus bus);

}
