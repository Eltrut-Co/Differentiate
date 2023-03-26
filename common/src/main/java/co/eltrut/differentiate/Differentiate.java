package co.eltrut.differentiate;

import co.eltrut.differentiate.platform.Helper;
import co.eltrut.differentiate.platform.Instance;
import co.eltrut.differentiate.platform.common.helpers.ItemHelper;
import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import co.eltrut.differentiate.platform.common.utility.TabUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

import java.util.function.Supplier;

//TODO: Add Forge & Fabric Sided Datagen things
public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Instance INSTANCE = Instance.create(MOD_ID).common(Differentiate::commonTest).build();
//    public static final CreativeModeTab EXAMPLE_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "example_tab"), () ->
//            new ItemStack(Differentiate.EXAMPLE_ITEM.get()));

//    public static final Helper<Item> ITEMS = Helper.create(Registry.ITEM, Differentiate.MOD_ID);
    public static final ItemHelper ITEMS = Helper.createItem(Differentiate.MOD_ID);
//    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
//            new Item(new Item.Properties().tab(Differentiate.EXAMPLE_TAB)));
    public static Supplier<Item> item;

    public static void commonTest() {
        item = ITEMS.createBoatAndChestBoatItem("basalt", Blocks.BASALT).getFirst();

        Differentiate.ITEMS.register();
        TabUtil.setInsertItemTab(Items.SAND,
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(Items.EMERALD),
                new ItemStack(Items.DIAMOND),
                new ItemStack(Items.NETHERITE_SCRAP));
        //        CraftingHelper.register(new BooleanRecipeCondition.Serializer("condition"));
    }

    public static void load() {
        INSTANCE.load();
    }
}