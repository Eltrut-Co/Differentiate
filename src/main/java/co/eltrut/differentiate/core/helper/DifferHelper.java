package co.eltrut.differentiate.core.helper;

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

	public static ItemHelper createItem(String modId) {
		return new ItemHelper(create(ForgeRegistries.ITEMS, modId));
	}

	public static BlockHelper createBlock(DifferHelper<Item> itemHelper) {
		return new BlockHelper(create(ForgeRegistries.BLOCKS, itemHelper.getModId()), itemHelper);
	}

	public static BlockHelper createBlock(ItemHelper itemHelper) {
		DifferHelper<Item> itemDifferHelper = itemHelper.getHelper();
		return new BlockHelper(create(ForgeRegistries.BLOCKS, itemDifferHelper.getModId()), itemDifferHelper);
	}

	public static BlockEntityHelper createBlockEntity(String modId) {
		return new BlockEntityHelper(create(ForgeRegistries.BLOCK_ENTITIES, modId));
	}

	public static EntityHelper createEntity(String modId) {
		return new EntityHelper(create(ForgeRegistries.ENTITIES, modId));
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