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

	public void register(IEventBus bus) {
		registry.register(bus);
	}

	public RegistryObject<T> register(String name, Supplier<T> supplier) {
		return registry.register(name, supplier);
	}
	
	public String getModId() {
		return this.modId;
	}

	public static BlockEntityHelper createBlock(DifferHelper<Item> itemRegistry) {
		return new BlockEntityHelper(create(ForgeRegistries.BLOCKS, itemRegistry.getModId()));
	}
}