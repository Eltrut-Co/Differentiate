package co.eltrut.differentiate.platform.common.helpers;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;

public class FuelHelper {
    @ExpectPlatform
    public static void registerFuel(ItemLike item, int ticks) {
        throw new AssertionError();
    }
}