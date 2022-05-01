package co.eltrut.differentiate.core.util.helper;

import co.eltrut.differentiate.core.mixin.FireBlockInvoker;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.tuple.Pair;

public class FlammablesHelper {
    public static void register(Block block, int flameOdds, int burnOdds) {
        ((FireBlockInvoker) Blocks.FIRE).callSetFlammable(block, flameOdds, burnOdds);
    }

    public static class Odds {
        public static final Pair<Integer, Integer> WOOD = Pair.of(5, 5);
        public static final Pair<Integer, Integer> PLANKS = Pair.of(5, 20);
        public static final Pair<Integer, Integer> BOOKSHELF = Pair.of(30, 20);
        public static final Pair<Integer, Integer> LEAVES = Pair.of(30, 60);
        public static final Pair<Integer, Integer> WOOL = Pair.of(30, 60);
        public static final Pair<Integer, Integer> CARPET = Pair.of(60, 20);
        public static final Pair<Integer, Integer> FLOWER = Pair.of(60, 100);
    }
}