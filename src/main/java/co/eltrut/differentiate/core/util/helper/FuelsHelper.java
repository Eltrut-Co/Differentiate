package co.eltrut.differentiate.core.util.helper;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class FuelsHelper {
    public static Map<Item, Integer> FUELS_MAP = new HashMap<>();

    public static void register(ItemLike item, int length) {
        FUELS_MAP.put(item.asItem(), length);
    }
}