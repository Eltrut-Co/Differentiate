package co.eltrut.differentiate.core.registrator;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class AbstractSubRegistrator<T extends IForgeRegistryEntry<T>> implements ISubRegistrator<T> {
	
	protected final Registrator parent;
	protected final DeferredRegister<T> registry;
	
	public AbstractSubRegistrator(Registrator parent, IForgeRegistry<T> registry) {
		this.parent = parent;
		this.registry = DeferredRegister.create(registry, parent.getModId());
	}
	
	public Registrator getParent() {
		return this.parent;
	}
	
	public DeferredRegister<T> getDeferredRegister() {
		return this.registry;
	}
	
	public void register(IEventBus bus) {
		this.registry.register(bus);
	}
	
}
