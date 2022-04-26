package co.eltrut.differentiate.core.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {

    @Inject(method = "fillItemList(Lnet/minecraft/core/NonNullList;)V", at = @At("TAIL"), cancellable = true)
    private void fillItemList(NonNullList<ItemStack> pItems, CallbackInfo ci) {
        Comparator<ItemStack> comparator = Comparator.comparing(s -> {
            return s.getItem().getRegistryName().getPath();
        });
        pItems.sort(comparator);
    }

}
