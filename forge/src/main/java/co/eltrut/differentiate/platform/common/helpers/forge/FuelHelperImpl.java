package co.eltrut.differentiate.platform.common.helpers.forge;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FuelHelperImpl {
    private static final Object2IntMap<ItemLike> FUEL_ENTRIES = new Object2IntOpenHashMap<>();

    static {
        MinecraftForge.EVENT_BUS.register(FuelHelperImpl.class);
    }

    public static void registerFuel(ItemLike item, int ticks) {
        FUEL_ENTRIES.put(item.asItem(), ticks);
    }

    @SubscribeEvent
    public static void onEvent(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().isEmpty()) return;
        int ticks = FUEL_ENTRIES.getOrDefault(event.getItemStack().getItem(), Integer.MIN_VALUE);
        if (ticks != Integer.MIN_VALUE) event.setBurnTime(ticks);
    }
}