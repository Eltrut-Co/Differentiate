package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.item.FuelItem;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemHelper extends AbstractHelper<Item, DeferredRegister.Items> {

	public ItemHelper(Registrator parent) {
		super(parent, DeferredRegister.createItems(parent.getModId()));
	}
	
	public DeferredItem<Item> createItem(String name, Supplier<Item> item) {
		return this.registry.register(name, item);
	}
	
//	public DeferredItem<Item> createSimpleItem(String name, CreativeModeTab group, String ...mods) {
//		return this.createItem(name, () -> new Item(GroupUtil.getProps(group, mods)));
//	}
//
//	public DeferredItem<Item> createFuelItem(String name, CreativeModeTab group, int burnTime, String ...mods) {
//		return this.createItem(name, () -> new FuelItem(GroupUtil.getProps(group, mods), burnTime));
//	}

}
