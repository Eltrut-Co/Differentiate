package co.eltrut.test.core.registry;

import co.eltrut.differentiate.core.registrator.ItemHelper;
import co.eltrut.test.core.Test;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestItems {
	
	public static final ItemHelper HELPER = Test.REGISTRATOR.getItemSubRegistrator();
	
	public static final RegistryObject<Item> ITEM = HELPER.createItem("item", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<Item> ITEM_TWO = HELPER.createItem("item_two", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));
	
}