package co.eltrut.differentiate.platform.common.helpers;

import co.eltrut.differentiate.platform.common.utility.TabUtil;
import co.eltrut.differentiate.platform.Helper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemHelper extends Helper<Item> {
	private final Helper<Item> helper;

	public ItemHelper(Helper<Item> helper) {
		super(helper.registry, helper.modId);
		this.helper = helper;
	}

	public Supplier<Item> createItem(String id, Supplier<Item> item) {
		return this.helper.register(id, item);
	}

	public Supplier<Item> createItemWithTab(String id, CreativeModeTab tab, String mods) {
		return this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
	}

	public Supplier<Item> createFuelItem(String id, CreativeModeTab tab, int length, String mods) {
		Supplier<Item> item = this.createItem(id, () -> new Item(TabUtil.getProps(tab, mods)));
		FuelHelper.registerFuel(item.get(), length);
		return item;
	}

	@Override
	public <E extends Item> Supplier<E> register(String key, Supplier<E> entry) {
		return helper.register(key, entry);
	}

	@Override
	public void load() {
		helper.load();
	}
}