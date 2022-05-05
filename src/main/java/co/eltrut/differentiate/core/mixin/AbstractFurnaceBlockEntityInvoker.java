package co.eltrut.differentiate.core.mixin;

import co.eltrut.differentiate.core.util.helper.FuelsHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityInvoker {
    @Inject(method = "getFuel", at = @At("RETURN"))
    private static void getFuel(CallbackInfoReturnable<Map<Item, Integer>> cir) {
        cir.getReturnValue().putAll(FuelsHelper.FUELS_MAP);
    }
}