package co.eltrut.differentiate.core.helper;

import java.util.function.Supplier;

import co.eltrut.differentiate.core.registrator.sub.ItemSubRegistrator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemHelper extends AbstractHelper<ItemSubRegistrator> {
	
	private String name;
	private Supplier<Item> item;

	public ItemHelper(ItemSubRegistrator parent) {
		super(parent);
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
		return this.parent.getDeferredRegister().register(this.name, this.item);
	}
	
}
