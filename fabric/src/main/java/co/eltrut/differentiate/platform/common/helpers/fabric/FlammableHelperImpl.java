package co.eltrut.differentiate.platform.common.helpers.fabric;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.block.Block;

public class FlammableHelperImpl {
    public static void register(Block block, int flameOdds, int burnOdds) {
        FlammableBlockRegistry.getDefaultInstance().add(block, flameOdds, burnOdds);
    }
}