package co.eltrut.differentiate.core.mixin;

import co.eltrut.differentiate.platform.common.utility.TabUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {
    @Inject(method = "fillItemList(Lnet/minecraft/core/NonNullList;)V", at = @At("TAIL"))
    private void modifyItemList(NonNullList<ItemStack> items, CallbackInfo ci) {
        Pair<Item, ItemStack[]> itemPair = TabUtil.getInsertItemTab();
        Item firstItem = itemPair.getFirst();
        ItemStack[] insertedItem = itemPair.getSecond();

        // Find the index of sand in the item list //
        int itemIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem() == firstItem) {
                itemIndex = i;
                break;
            }
        }

        if (itemIndex != -1) {
            // Sort the items by name //
            List<ItemStack> itemsToAddAfter = new ArrayList<>(Arrays.asList(insertedItem));
            itemsToAddAfter.sort(Comparator.comparing(itemStack -> itemStack.getDisplayName().getString()));
            items.addAll(itemIndex + 1, itemsToAddAfter);
        }
    }
}