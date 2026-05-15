package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreativeTabEntry {

    private final DeferredItem<?> item;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final ItemLike followItem;

    public CreativeTabEntry(DeferredItem<?> item, List<ResourceKey<CreativeModeTab>> tabs, String[] compatMods, ItemLike followItem) {
        this.item = item;
        this.followItem = followItem;
        this.tabs = checkCompatibility(tabs, compatMods);

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabEntry(DeferredItem<?> item, ResourceKey<CreativeModeTab> tab, String[] compatMods, ItemLike followItem) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    public CreativeTabEntry(DeferredItem<?> item, ResourceKey<CreativeModeTab> tab, String[] compatMods) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, null);
    }

    protected void assignTabs(BuildCreativeModeTabContentsEvent event) {
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

    private List<ResourceKey<CreativeModeTab>> checkCompatibility(List<ResourceKey<CreativeModeTab>> tabs,
                                                                  String[] compatMods) {
        if (CompatUtil.areModsLoaded(compatMods)) {
            // TODO: how to hide from search tab?
            return new ArrayList<>(tabs);
        }
        return Collections.emptyList();
    }

}
