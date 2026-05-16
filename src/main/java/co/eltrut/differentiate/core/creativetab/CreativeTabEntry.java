package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreativeTabEntry extends AbstractCreativeTabEntry {

    private final DeferredItem<? extends Item> item;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final ItemLike followItem;

    public CreativeTabEntry(DeferredItem<? extends Item> item, List<ResourceKey<CreativeModeTab>> tabs, String[] compatMods, ItemLike followItem) {
        this.item = item;
        this.followItem = followItem;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, ResourceKey<CreativeModeTab> tab, String[] compatMods, ItemLike followItem) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, ResourceKey<CreativeModeTab> tab, String[] compatMods) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, null);
    }

    @Override
    public void assignTabs(BuildCreativeModeTabContentsEvent event) {
        for (ResourceKey<CreativeModeTab> tab : this.tabs) {
            if (event.getTabKey() == tab) {
                if (this.followItem == null) {
                    event.accept(this.item.toStack());
                } else {
                    event.insertAfter(this.followItem.asItem().getDefaultInstance(), this.item.toStack(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
    }

}
