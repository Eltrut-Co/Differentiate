package co.eltrut.differentiate.platform.common.helpers.forge;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostHelperImpl {
    public static void register(ItemLike item, float odds) {
        ComposterBlock.COMPOSTABLES.put(item, odds);
    }
}