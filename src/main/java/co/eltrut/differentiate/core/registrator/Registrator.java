package co.eltrut.differentiate.core.registrator;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Registrator<T extends IForgeRegistryEntry<T>> {
	private final String modId;

	public Registrator(String modId) {
		this.modId = modId;

	}
	
	public static Registrator create(IForgeRegistry<T> registry, String modId) {
		Registrator registrator = new Registrator(modId);
		consumer.accept(registrator);
		return registrator;
	}
	
	public void register(IEventBus bus) {
		for (IHelper<?> helper : this.helpers.values()) {
			helper.register(bus);
		}
	}
	
	public String getModId() {
		return this.modId;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IHelper<?>> T getHelper(IForgeRegistry<?> registry) {
		return (T) this.helpers.get(registry);
	}
	
	public Map<IForgeRegistry<?>, IHelper<?>> getHelpers() {
		return this.helpers;
	}
	
	public static <T extends IForgeRegistryEntry<T>> void registerAttribute(IForgeRegistry<T> registry, Class<? extends Interface> clazz, Consumer<T> consumer) {
		registry.getValues().stream().filter(clazz::isInstance).forEach(consumer);
	}
}