package co.eltrut.differentiate.platform.common.helpers.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorHelperImpl {
    public static void registerBlockColor(BlockColor color, Block... blocks) {
        ColorProviderRegistry.BLOCK.register(color, blocks);
    }

    public static void registerItemColor(ItemColor color, ItemLike...items) {
        ColorProviderRegistry.ITEM.register(color, items);
    }
}