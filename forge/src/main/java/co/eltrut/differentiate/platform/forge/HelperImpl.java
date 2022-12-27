package co.eltrut.differentiate.platform.forge;

import co.eltrut.differentiate.platform.Helper;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class HelperImpl<T> extends Helper<T> {
    private final DeferredRegister<T> registry;

    protected HelperImpl(Registry<T> registry, String modId) {
        super(registry, modId);
        this.registry = DeferredRegister.create(registry.key(), modId);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Helper<T> create(Registry<T> registry, String modId) {
        return new HelperImpl(registry, modId);
    }

    @Override
    public <E extends T> Supplier<E> register(String key, Supplier<E> entry) {
        return this.registry.register(key, entry);
    }

    @Override
    public void load() {
        this.registry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}