package co.eltrut.differentiate.core.helper;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractHelper<T> implements IHelper {

    protected final DifferHelper parent;
    protected final DeferredRegister<T> deferredRegister;

    public AbstractHelper(DifferHelper parent, IForgeRegistry<T> registry) {
        this.parent = parent;
        this.deferredRegister = DeferredRegister.create(registry, parent.getId());
    }

    @Override
    public DifferHelper getParent() {
        return this.parent;
    }

    @Override
    public DeferredRegister<T> getDeferredRegister() {
        return this.deferredRegister;
    }

    @Override
    public void register(IEventBus bus) {
        this.deferredRegister.register(bus);
    }

    public <U> List<RegistryObject<T>> createMultipleEntries(U[] array, Function<? super U, ? extends RegistryObject<T>> mapper) {
        return Arrays.stream(array).map(mapper).collect(Collectors.toList());
    }

}
