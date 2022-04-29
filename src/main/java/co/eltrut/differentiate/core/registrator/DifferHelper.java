package co.eltrut.differentiate.core.registrator;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class DifferHelper<T extends IForgeRegistryEntry<T>> {
	private final String modId;
	private final DeferredRegister<T> registry;

	private DifferHelper(IForgeRegistry<T> registry, String modId) {
		this.modId = modId;
		this.registry = DeferredRegister.create(registry, modId);
	}
	
	public static <T extends IForgeRegistryEntry<T>> DifferHelper<T> create(IForgeRegistry<T> registry, String modId) {
		return new DifferHelper<>(registry, modId);
	}
	
	public String getModId() {
		return this.modId;
	}
}