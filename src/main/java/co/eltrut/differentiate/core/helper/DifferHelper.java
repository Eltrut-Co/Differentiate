package co.eltrut.differentiate.core.helper;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DifferHelper {
	private final String id;
	private final Map<IForgeRegistry<?>, IHelper<?>> helpers = new HashMap<>();

	public DifferHelper(String id) {
		this.id = id;

		this.helpers.put(ForgeRegistries.ITEMS, new ItemHelper(this));
		this.helpers.put(ForgeRegistries.BLOCKS, new BlockHelper(this));
		this.helpers.put(ForgeRegistries.BLOCK_ENTITY_TYPES, new BlockEntityHelper(this));
		this.helpers.put(ForgeRegistries.ENTITY_TYPES, new EntityHelper(this));
	}

	public static DifferHelper create(String modid, Consumer<DifferHelper> consumer) {
		DifferHelper helper = new DifferHelper(modid);
		consumer.accept(helper);
		return helper;
	}
	
	public String getId() {
		return this.id;
	}

	public <T extends IHelper<?>> T getHelper(IForgeRegistry<?> registry) {
		return (T) this.helpers.get(registry);
	}

	public void register(IEventBus bus) {
		for (IHelper<?> helper : this.helpers.values()) {
			helper.register(bus);
		}
	}
}