package co.eltrut.differentiate.core.util.helper;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;

public class FlammablesHelper {
    public static Object2IntMap<Block> FLAME_CHANCE_MAP = new Object2IntOpenHashMap<>();
    public static Object2IntMap<Block> BURN_CHANCE_MAP = new Object2IntOpenHashMap<>();

    public static void register(Block block, int flameChance, int burnChance) {
        FLAME_CHANCE_MAP.put(block, flameChance);
        BURN_CHANCE_MAP.put(block, burnChance);
    }

    public static class Chances {
        public static final Pair<Integer, Integer> WOOD = Pair.of(5, 5);
        public static final Pair<Integer, Integer> PLANKS = Pair.of(5, 20);
        public static final Pair<Integer, Integer> BOOKSHELF = Pair.of(30, 20);
        public static final Pair<Integer, Integer> LEAVES = Pair.of(30, 60);
        public static final Pair<Integer, Integer> WOOL = Pair.of(30, 60);
        public static final Pair<Integer, Integer> CARPET = Pair.of(60, 20);
        public static final Pair<Integer, Integer> FLOWER = Pair.of(60, 100);
    }
}