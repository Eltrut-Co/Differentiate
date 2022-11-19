package co.eltrut.differentiate.platform.common.helpers.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;

public class CompostHelperImpl {
    public static void register(ItemLike item, float odds) {
        CompostingChanceRegistry.INSTANCE.add(item, odds);
    }
}