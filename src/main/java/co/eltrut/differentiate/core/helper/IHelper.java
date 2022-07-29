package co.eltrut.differentiate.core.helper;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

public interface IHelper<T extends IForgeRegistry<T>> {

    public DifferHelper getParent();
    public DeferredRegister<T> getDeferredRegister();
    public void register(IEventBus bus);

}
