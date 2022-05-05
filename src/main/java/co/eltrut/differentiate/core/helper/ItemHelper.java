package co.eltrut.differentiate.core.helper;

import co.eltrut.differentiate.core.util.TabUtil;
import co.eltrut.differentiate.core.util.helper.FuelsHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Supplier;

public final class ItemHelper extends DifferHelper<Item> {
	private final DifferHelper<Item> itemHelper;

	public ItemHelper(DifferHelper<Item> itemHelper) {
		super(itemHelper);
		this.itemHelper = itemHelper;
	}

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

	public DifferHelper<Item> itemHelper() {
		return itemHelper;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (ItemHelper) obj;
		return Objects.equals(this.itemHelper, that.itemHelper);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemHelper);
	}

	@Override
	public String toString() {
		return "ItemHelper[" +
				"itemHelper=" + itemHelper + ']';
	}

}