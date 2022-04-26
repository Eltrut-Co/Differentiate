package co.eltrut.test.core.registry;

import co.eltrut.differentiate.removal.registrator.ItemHelper;
import co.eltrut.test.core.Test;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestItems {
	
	public static final ItemHelper HELPER = Test.REGISTRATOR.getHelper(ForgeRegistries.ITEMS);
	
	public static final RegistryObject<Item> ITEM = HELPER.createItem("item", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> ITEM_TWO = HELPER.createItem("item_two", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
	
}