package co.eltrut.differentiate.platform.common.helpers.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.level.ItemLike;

public class FuelHelperImpl {
    public static void registerFuel(ItemLike item, int ticks) {
        FuelRegistry.INSTANCE.add(item, ticks);
    }
}