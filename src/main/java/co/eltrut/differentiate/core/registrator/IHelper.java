package co.eltrut.differentiate.core.registrator;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface IHelper<T> {
	
	public Registrator getParent();
	public DeferredRegister<T> getDeferredRegister();
	public void register(IEventBus bus);

}
