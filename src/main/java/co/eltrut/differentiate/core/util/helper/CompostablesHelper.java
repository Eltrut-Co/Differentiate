package co.eltrut.differentiate.core.util.helper;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.world.level.ItemLike;

public class CompostablesHelper {
    public static Object2FloatMap<ItemLike> COMPOSTABLES_MAP = new Object2FloatOpenHashMap<>();

    public static void register(ItemLike item, float compostableChance) {
        COMPOSTABLES_MAP.put(item, compostableChance);
    }

    public static class Chances {
        public static final float SEEDS = 0.3F;
        public static final float PLANTS = 0.65F;
        public static final float BAKED_GOODS = 0.85F;
        public static final float PIES = 1.0F;
    }
}
