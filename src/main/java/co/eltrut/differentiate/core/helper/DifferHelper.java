package co.eltrut.differentiate.core.helper;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class DifferHelper<T> {
	private final String id;
	private final DeferredRegister<T> deferredRegister;

	public DifferHelper(IForgeRegistry<T> borgeRegistry, String id) {
		this.id = id;
		this.deferredRegister = DeferredRegister.create(borgeRegistry, id);
	}

	public static ItemHelper constructItem(String modId) {
		return new ItemHelper(new DifferHelper<>(ForgeRegistries.ITEMS, modId));
	}

	public static BlockHelper constructBlock(DifferHelper<Item> itemHelper) {
		return new BlockHelper(new DifferHelper<>(ForgeRegistries.BLOCKS, itemHelper.getId()), itemHelper);
	}

	public static BlockEntityHelper constructBlockEntity(String modId) {
		return new BlockEntityHelper(new DifferHelper<>(ForgeRegistries.BLOCK_ENTITY_TYPES, modId));
	}

	public static EntityHelper constructEntity(String modId) {
		return new EntityHelper(new DifferHelper<>(ForgeRegistries.ENTITY_TYPES, modId));
	}

	public void setBus(IEventBus bus) {
		deferredRegister.register(bus);
	}

	public RegistryObject<T> register(String name, Supplier<T> supplier) {
		return deferredRegister.register(name, supplier);
	}
	
	public String getId() {
		return this.id;
	}
}