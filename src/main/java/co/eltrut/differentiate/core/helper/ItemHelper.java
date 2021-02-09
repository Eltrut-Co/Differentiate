package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHelper extends AbstractHelper<Item> {

	public ItemHelper(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public RegistryObject<Item> item(String name, Supplier<Item> item) {
		return this.REGISTRY.register(name, item);
	}

}
