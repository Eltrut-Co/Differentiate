package co.eltrut.differentiate.core.registrator;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

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

	public static BlockHelper createBlock(DifferHelper<Item> itemRegistry) {
		return new BlockHelper(create(ForgeRegistries.BLOCKS, itemRegistry.getModId()), itemRegistry);
	}

	public void setRegistry(IEventBus bus) {
		registry.register(bus);
	}

	public RegistryObject<T> register(String name, Supplier<T> supplier) {
		return registry.register(name, supplier);
	}
	
	public String getModId() {
		return this.modId;
	}
}