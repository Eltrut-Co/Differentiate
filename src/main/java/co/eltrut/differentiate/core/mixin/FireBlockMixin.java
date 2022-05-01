package co.eltrut.differentiate.core.mixin;

import co.eltrut.differentiate.core.util.helper.FlammablesHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FireBlock.class)
public class FireBlockMixin {
    @Shadow
    @Final
    @Mutable
    private static Object2IntMap<Block> flameOdds;
    @Shadow
    @Final
    @Mutable
    private static Object2IntMap<Block> burnOdds;

    static {
        flameOdds = new Object2IntOpenHashMap<>(flameOdds);
        burnOdds = new Object2IntOpenHashMap<>(burnOdds);
        flameOdds.putAll(FlammablesHelper.FLAME_CHANCE_MAP);
        burnOdds.putAll(FlammablesHelper.BURN_CHANCE_MAP);
    }
}