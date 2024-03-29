package co.eltrut.differentiate.platform.common.helpers.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorHelperImpl {
    public static void registerBlockColor(BlockColor color, Block... blocks) {
        Minecraft.getInstance().getBlockColors().register(color, blocks);
    }

    public static void registerItemColor(ItemColor color, ItemLike...items) {
        Minecraft.getInstance().getItemColors().register(color, items);
    }
}