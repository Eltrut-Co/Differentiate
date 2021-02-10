package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHelper extends AbstractHelper<Item> {

	private String name;
	private Supplier<Item> item;
	
	public ItemHelper(Registrator parent) {
		super(parent, ForgeRegistries.ITEMS);
	}
	
	public ItemHelper setName(String name) {
		this.name = name;
		return this;
	}
	
	public ItemHelper setItem(Supplier<Item> item) {
		this.item = item;
		return this;
	}
	
	public RegistryObject<Item> build() {
		return this.REGISTRY.register(this.name, this.item);
	}
	
	public RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
		return this.setName(name).setItem(item).build();
	}

}
