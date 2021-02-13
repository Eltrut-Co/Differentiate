package co.eltrut.differentiate.core.registry;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.registrator.sub.ItemSubRegistrator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Differentiate.MOD_ID, bus = Bus.MOD)
public class DifferentiateItems {
	
	public static final ItemSubRegistrator HELPER = Differentiate.REGISTRATOR.getItemSubRegistrator();
	
	public static final RegistryObject<Item> ITEM = HELPER.createItem("item", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> ITEM_TWO = HELPER.createItem("item_two", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));
	
}