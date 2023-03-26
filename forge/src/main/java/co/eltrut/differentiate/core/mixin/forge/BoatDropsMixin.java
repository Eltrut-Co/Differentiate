package co.eltrut.differentiate.core.mixin.forge;

import co.eltrut.differentiate.Differentiate;
import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public class BoatDropsMixin {
    @Inject(method = "getDropItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> ci) {
//        if (((Boat)(Object)this).getBoatType() == CustomBoatType.BASALT) {
//            ci.setReturnValue(Differentiate.item.get());
//        }
    }
}