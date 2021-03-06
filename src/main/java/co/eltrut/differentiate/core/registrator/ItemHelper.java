package co.eltrut.differentiate.core.registrator;

import java.util.function.Supplier;

import co.eltrut.differentiate.common.item.FuelItem;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHelper extends AbstractHelper<Item> {

	public ItemHelper(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public RegistryObject<Item> createItem(String name, Supplier<Item> item) {
		return this.registry.register(name, item);
	}
	
	public RegistryObject<Item> createSimpleItem(String name, ItemGroup group, String ...mods) {
		return this.createItem(name, () -> new Item(GroupUtil.getProps(group, mods)));
	}
	
	public RegistryObject<Item> createFuelItem(String name, Item.Properties props, int burnTime) {
		return this.registry.register(name, () -> new FuelItem(props, burnTime));
	}

}
