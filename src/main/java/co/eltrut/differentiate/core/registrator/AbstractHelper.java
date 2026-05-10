package co.eltrut.differentiate.core.registrator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	
//	public <U> List<RegistryObject<T>> createMultipleEntries(U[] array, Function<? super U, ? extends RegistryObject<T>> mapper) {
//		return Arrays.stream(array).map(mapper).collect(Collectors.toList());
//	}
	
}
