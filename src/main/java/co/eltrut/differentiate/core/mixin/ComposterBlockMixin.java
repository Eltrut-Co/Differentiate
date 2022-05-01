package co.eltrut.differentiate.core.mixin;

import co.eltrut.differentiate.core.util.helper.CompostablesHelper;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {
    @Shadow
    @Final
    @Mutable
    protected static Object2FloatMap<ItemLike> COMPOSTABLES;

    static {
        COMPOSTABLES = new Object2FloatOpenHashMap<>(COMPOSTABLES);
        COMPOSTABLES.putAll(CompostablesHelper.COMPOSTABLES_MAP);
    }
}