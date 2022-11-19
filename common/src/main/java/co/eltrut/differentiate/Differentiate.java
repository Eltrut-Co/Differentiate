package co.eltrut.differentiate;

import co.eltrut.differentiate.platform.Helper;
import co.eltrut.differentiate.platform.Instance;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Instance INSTANCE = Instance.create(MOD_ID).common(Differentiate::commonTest).build();
//    public static final CreativeModeTab EXAMPLE_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "example_tab"), () ->
//            new ItemStack(Differentiate.EXAMPLE_ITEM.get()));

    public static final Helper<Item> ITEMS = Helper.create(Registry.ITEM, Differentiate.MOD_ID);
//    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
//            new Item(new Item.Properties().tab(Differentiate.EXAMPLE_TAB)));
    
    public static void commonTest() {
        Differentiate.ITEMS.register();
        //        CraftingHelper.register(new BooleanRecipeCondition.Serializer("condition"));
    }

    public static void load() {
        INSTANCE.load();
    }
}