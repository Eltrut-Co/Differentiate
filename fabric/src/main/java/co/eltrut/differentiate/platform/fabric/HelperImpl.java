package co.eltrut.differentiate.platform.fabric;

import co.eltrut.differentiate.platform.Helper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class HelperImpl<T> extends Helper<T> {
    private HelperImpl(Registry<T> registry, String modId) {
        super(registry, modId);
    }

    public static <T> Helper<T> create(Registry<T> registry, String modId) {
        return new HelperImpl<>(registry, modId);
    }

    @Override
    public <E extends T> Supplier<E> register(String key, Supplier<E> entry) {
        E registry = Registry.register(this.registry, new ResourceLocation(this.modId, key), entry.get());
        return () -> registry;
    }

    @Override
    public void load() {}
}