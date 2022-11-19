package co.eltrut.differentiate.platform.common.helpers;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;

public class CompostHelper {
    @ExpectPlatform
    public static void register(ItemLike item, float odds) {
        throw new AssertionError();
    }

    public static class Odds {
        public static final float SEEDS = 0.3F;
        public static final float PLANTS = 0.65F;
        public static final float BAKED_GOODS = 0.85F;
        public static final float PIES = 1.0F;
    }
}