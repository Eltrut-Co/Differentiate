package co.eltrut.differentiate.core.registrator.sub;

import co.eltrut.differentiate.core.registrator.Registrator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class AbstractSubRegistrator<T extends IForgeRegistryEntry<T>> implements ISubRegistrator<T> {
	
	protected final Registrator PARENT;
	protected final DeferredRegister<T> REGISTRY;
	
	public AbstractSubRegistrator(Registrator parent, IForgeRegistry<T> registry) {
		this.PARENT = parent;
		this.REGISTRY = DeferredRegister.create(registry, parent.getModId());
	}
	
	public Registrator getParent() {
		return this.PARENT;
	}
	
	public DeferredRegister<T> getDeferredRegister() {
		return this.REGISTRY;
	}
	
	public void register(IEventBus bus) {
		REGISTRY.register(bus);
	}
	
}
