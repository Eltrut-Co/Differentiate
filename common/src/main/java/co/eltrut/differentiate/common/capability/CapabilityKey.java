package co.eltrut.differentiate.common.capability;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Optional;

public interface CapabilityKey<T extends Capability> {
    ResourceLocation getId();

    Class<T> getCapabilityClass();

    @Nullable
    <V> T get(@Nullable V provider);

    <V> Optional<T> getOptional(@Nullable V provider);
}