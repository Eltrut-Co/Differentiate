package co.eltrut.differentiate.core.util.helper;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostablesHelper {
    public static void register(ItemLike item, float odds) {
        ComposterBlock.COMPOSTABLES.put(item, odds);
    }

    public static class Odds {
        public static final float SEEDS = 0.3F;
        public static final float PLANTS = 0.65F;
        public static final float BAKED_GOODS = 0.85F;
        public static final float PIES = 1.0F;
    }
}
