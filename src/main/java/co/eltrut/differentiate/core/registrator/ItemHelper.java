package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.core.creativetab.CreativeTabEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemHelper extends AbstractHelper<Item, DeferredRegister.Items> {

	public ItemHelper(Registrator parent) {
		super(parent, DeferredRegister.createItems(parent.getModId()));
	}

	protected DeferredItem<Item> createItemWithoutEntry(String name, Supplier<Item> item) {
		return this.registry.register(name, item);
	}

	public DeferredItem<Item> createItem(String name, Supplier<Item> item, ResourceKey<CreativeModeTab> tab,
										 String ...mods) {
		DeferredItem<Item> registeredItem = this.registry.register(name, item);
		CreativeTabEntry entry = new CreativeTabEntry(registeredItem, tab, mods);

		return registeredItem;
	}

	public DeferredItem<Item> createFollowItem(String name, Supplier<Item> item, ResourceKey<CreativeModeTab> tab,
                                               ItemLike followItem, String ...mods) {
		DeferredItem<Item> registeredItem = this.registry.register(name, item);
		CreativeTabEntry entry = new CreativeTabEntry(registeredItem, tab, mods, followItem);

		return registeredItem;
	}

	/*
	Note that items from other mods can only be safely queried after registration is complete.
	This method allows for this querying to occur when building the creative tab, preventing incompatibilities with mods.
	 */
	public DeferredItem<Item> createFollowItem(String name, Supplier<Item> item, ResourceKey<CreativeModeTab> tab,
	                                           String modid, String followItem, String ...mods) {
		DeferredItem<Item> registeredItem = this.registry.register(name, item);
		CreativeTabEntry entry = new CreativeTabEntry(registeredItem, tab, mods, modid, followItem);

		return registeredItem;
	}

}
