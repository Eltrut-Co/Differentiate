package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.item.FuelItem;
import co.eltrut.differentiate.core.creativetab.CreativeTabEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemHelper extends AbstractHelper<Item, DeferredRegister.Items> {

	public ItemHelper(Registrator parent) {
		super(parent, DeferredRegister.createItems(parent.getModId()));
	}
	
	public DeferredItem<Item> createItem(String name, Supplier<Item> item, ResourceKey<CreativeModeTab> tab,
										 String ...mods) {
		DeferredItem<Item> registeredItem = this.registry.register(name, item);
		CreativeTabEntry entry = new CreativeTabEntry(registeredItem, tab, mods, Items.DIRT);

		return registeredItem;
	}

	public DeferredItem<Item> createFuelItem(String name, CreativeModeTab group, Item.Properties props, int burnTime,
											 ResourceKey<CreativeModeTab> tab, String ...mods) {
		return this.createItem(name, () -> new FuelItem(props, burnTime), tab, mods);
	}

}
