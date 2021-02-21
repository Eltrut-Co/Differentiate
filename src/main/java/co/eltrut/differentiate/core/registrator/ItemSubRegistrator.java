package co.eltrut.differentiate.core.registrator;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemSubRegistrator extends AbstractSubRegistrator<Item> {

	public ItemSubRegistrator(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public RegistryObject<Item> createItem(String name, Supplier<Item> item) {
		return this.registry.register(name, item);
	}

}
