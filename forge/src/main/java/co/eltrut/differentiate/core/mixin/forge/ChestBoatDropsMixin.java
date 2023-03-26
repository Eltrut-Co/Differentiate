package co.eltrut.differentiate.core.mixin.forge;

import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoat.class)
public class ChestBoatDropsMixin {
    @Inject(method = "getDropItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> ci) {
//        if (((ChestBoat)(Object)this).getBoatType() == CustomBoatType.BASALT) {
//            ci.setReturnValue(Differentiate.item.get());
//        }
    }
}