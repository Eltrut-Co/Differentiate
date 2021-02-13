package co.eltrut.differentiate.core.registrator.sub;

import java.util.function.Supplier;

import co.eltrut.differentiate.core.helper.ItemHelper;
import co.eltrut.differentiate.core.registrator.Registrator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemSubRegistrator extends AbstractSubRegistrator<Item> {

	public ItemSubRegistrator(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public ItemHelper create() {
		return new ItemHelper(this);
	}
	
	public RegistryObject<Item> createItem(String name, Supplier<Item> item) {
		return new ItemHelper(this).setName(name).setItem(item).build();
	}

}
