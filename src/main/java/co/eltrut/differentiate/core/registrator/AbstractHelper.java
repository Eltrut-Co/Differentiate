package co.eltrut.differentiate.core.registrator;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractHelper<T extends IForgeRegistryEntry<T>> implements IHelper<T> {
	
	protected final Registrator parent;
	protected final DeferredRegister<T> registry;
	
	public AbstractHelper(Registrator parent, IForgeRegistry<T> registry) {
		this.parent = parent;
		this.registry = DeferredRegister.create(registry, parent.getModId());
	}
	
	@Override
	public Registrator getParent() {
		return this.parent;
	}
	
	@Override
	public DeferredRegister<T> getDeferredRegister() {
		return this.registry;
	}
	
	@Override
	public void register(IEventBus bus) {
		this.registry.register(bus);
	}
	
}
