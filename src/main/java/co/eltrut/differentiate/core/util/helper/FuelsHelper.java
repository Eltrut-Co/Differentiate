package co.eltrut.differentiate.core.util.helper;

import co.eltrut.differentiate.core.mixin.AbstractFurnaceBlockEntityInvoker;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.Map;

public class FuelsHelper {
    public static Map<Item, Integer> FUELS_MAP = new HashMap<>();

    public static void register(ItemLike item, int length) {
        ((AbstractFurnaceBlockEntityInvoker) BlockEntityType.FURNACE).callAdd(FUELS_MAP, item, length);
    }
}