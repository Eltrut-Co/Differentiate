package co.eltrut.differentiate.core.mixin;

import co.eltrut.differentiate.core.util.helper.StrippablesHelper;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Shadow
    @Final
    @Mutable
    protected static Map<Block, Block> STRIPPABLES;

    static {
        STRIPPABLES = new HashMap<>(STRIPPABLES);
        STRIPPABLES.putAll(StrippablesHelper.STRIPPABLES_MAP);
    }
}