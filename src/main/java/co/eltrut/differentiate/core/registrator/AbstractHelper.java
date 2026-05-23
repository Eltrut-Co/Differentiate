package co.eltrut.differentiate.core.registrator;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public abstract class AbstractHelper<T, R extends DeferredRegister<T>> implements IHelper<T> {
	
	protected final Registrator parent;
	protected final R registry;
	
	public AbstractHelper(Registrator parent, R registry) {
		this.parent = parent;
		this.registry = registry;
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
