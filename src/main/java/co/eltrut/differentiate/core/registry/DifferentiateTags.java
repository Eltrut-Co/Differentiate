package co.eltrut.differentiate.core.registry;

import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DifferentiateTags {

    public static final TagKey<Block> WOOD_SLABS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_slabs"));
    public static final TagKey<Block> WOOD_STAIRS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_stairs"));
    public static final TagKey<Block> WOOD_VERTICAL_SLABS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_vertical_slabs"));
    public static final TagKey<Block> WOOD_WALLS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_walls"));

    public static final TagKey<Item> WOOD_SLABS_ITEM = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_slabs"));
    public static final TagKey<Item> WOOD_STAIRS_ITEM = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_stairs"));
    public static final TagKey<Item> WOOD_VERTICAL_SLABS_ITEM = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_vertical_slabs"));
    public static final TagKey<Item> WOOD_WALLS_ITEM = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Differentiate.MOD_ID, "wood_walls"));

}
