package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.core.util.TabUtil;
import co.eltrut.differentiate.core.util.helper.FuelsHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public record ItemHelper(DifferHelper<Item> itemHelper) {
	public RegistryObject<Item> createItem(String id, Supplier<Item> item) {
		return itemHelper.register(id, item);
	}
	
	public RegistryObject<Item> createItemWithTab(String id, CreativeModeTab tab, String mods) {
		return this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
	}
	
	public RegistryObject<Item> createFuelItem(String id, CreativeModeTab tab, int length, String mods) {
		RegistryObject<Item> item = this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
		FuelsHelper.register(item.get(), length);
		return item;
	}
}