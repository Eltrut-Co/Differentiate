package co.eltrut.differentiate.platform.common.helpers.forge;

import co.eltrut.differentiate.core.mixin.FireBlockInvoker;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FlammableHelperImpl {
    public static void register(Block block, int flameOdds, int burnOdds) {
        ((FireBlockInvoker) Blocks.FIRE).callSetFlammable(block, flameOdds, burnOdds);
    }
}