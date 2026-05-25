package co.eltrut.differentiate.core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ItemUtil {

    public static Item getItemFromId(String namespace, String path) {
        if (BuiltInRegistries.ITEM.containsKey(ResourceLocation.fromNamespaceAndPath(namespace, path))) {
            return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(namespace, path));
        }
        return null;
    }

    public static String getIdFromItem(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

}
