package co.eltrut.differentiate.platform.common.helpers;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class StrippablesHelper {
    public static Map<Block, Block> STRIPPABLES_MAP = new HashMap<>();

    public static void register(Block log, Block strippedLog) {
        STRIPPABLES_MAP.put(log, strippedLog);
    }
}