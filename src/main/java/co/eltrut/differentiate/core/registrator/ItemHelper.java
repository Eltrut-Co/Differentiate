package co.eltrut.differentiate.core.registrator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHelper extends AbstractHelper<Item> {
	
	public static final Map<RegistryObject<Item>, Integer> FUEL = new HashMap<>(); 

	public ItemHelper(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public RegistryObject<Item> createItem(String name, Supplier<Item> item) {
		return this.registry.register(name, item);
	}
	
	public RegistryObject<Item> createSimpleItem(String name, CreativeModeTab group, String ...mods) {
		return this.createItem(name, () -> new Item(GroupUtil.getProps(group, mods)));
	}
	
//	public RegistryObject<Item> createFuelItem(String name, Supplier<Item> item, int burnTime) {
//		RegistryObject<Item> registeredItem = this.createItem(name, item);
//		FUEL.put(registeredItem, burnTime);
//		return registeredItem;
//	}

}
