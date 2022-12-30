package co.eltrut.differentiate.common.capability;

import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public interface CapabilityKey<T extends Capability> {
    ResourceLocation getId();

    Class<T> getCapabilityClass();

    <V> T get(V provider);

    <V> Optional<T> getOptional(V provider);
}