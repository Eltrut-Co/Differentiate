package co.eltrut.test.core.registry;

import co.eltrut.differentiate.core.helper.DifferHelper;
import co.eltrut.differentiate.core.helper.ItemHelper;
import co.eltrut.test.core.Test;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Test.MOD_ID, bus = Bus.MOD)
public class TestItems {
	public static final ItemHelper HELPER = DifferHelper.createItem(Test.MOD_ID);
	
	public static final RegistryObject<Item> ITEM = HELPER.createItem("item", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
	public static final RegistryObject<Item> ITEM_TWO = HELPER.createItem("item_two", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING)));
}