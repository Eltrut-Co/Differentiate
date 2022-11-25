package co.eltrut.differentiate.platform.common.helpers;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorHelper {
    @ExpectPlatform
    public static void registerBlockColor(BlockColor color, Block...blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerItemColor(ItemColor color, ItemLike...items) {
        throw new AssertionError();
    }
}