package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.TabUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public record ItemHelper(DifferHelper<Item> itemHelper) {
	public RegistryObject<Item> createItem(String name, Supplier<Item> item) {
		return itemHelper.register(name, item);
	}
	
	public RegistryObject<Item> createSimpleItem(String name, CreativeModeTab group, String mods) {
		return this.createItem(name, () -> new Item(TabUtil.getProps(group, mods)));
	}
	
	public RegistryObject<Item> createFuelItem(String name, CreativeModeTab group, int burnTime, String mods) {
		RegistryObject<Item> item = this.createItem(name, () -> new Item(TabUtil.getProps(group, mods)));
		CompatUtil.registerFuel(item.get(), burnTime);
		return item;
	}
}