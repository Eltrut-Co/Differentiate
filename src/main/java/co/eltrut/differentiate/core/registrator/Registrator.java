package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.interf.*;
import co.eltrut.differentiate.core.util.DataUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Registrator {
	
	private static final Logger LOGGER = LogManager.getLogger();
	public static final List<Registrator> REGISTRATORS = new ArrayList<>();
	
	private final String modid;
	private final Map<ResourceKey<? extends Registry<?>>, IHelper<?>> helpers = new HashMap<>();
	
	public Registrator(String modid) {
		this.modid = modid;
		REGISTRATORS.add(this);
		
		this.helpers.put(Registries.ITEM, new ItemHelper(this));
		this.helpers.put(Registries.BLOCK, new BlockHelper(this));
		this.helpers.put(Registries.BLOCK_ENTITY_TYPE, new BlockEntityHelper(this));
	}
	
	public static Registrator create(String modid, Consumer<Registrator> consumer) {
		Registrator registrator = new Registrator(modid);
		consumer.accept(registrator);
		return registrator;
	}
	
	public void register(IEventBus bus) {
		for (IHelper<?> helper : this.helpers.values()) {
			helper.register(bus);
		}
	}
	
	public String getModId() {
		return this.modid;
	}

	public <T, R extends IHelper<T>> R getHelper(ResourceKey<Registry<T>> registry) {
		return (R) this.helpers.get(registry);
	}
	
	public Map<ResourceKey<? extends Registry<?>>, IHelper<?>> getHelpers() {
		return this.helpers;
	}
	
}