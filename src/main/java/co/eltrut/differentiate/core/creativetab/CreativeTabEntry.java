package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreativeTabEntry {

    private final DeferredItem<?> item;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final String[] compatMods;
    private final String followItem;

    public CreativeTabEntry(DeferredItem<?> item, List<ResourceKey<CreativeModeTab>> tabs, String[] compatMods, String followItem) {
        this.item = item;
        this.compatMods = compatMods;
        this.followItem = followItem;
        this.tabs = checkCompatibility(tabs, compatMods);

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabEntry(DeferredItem<?> item, ResourceKey<CreativeModeTab> tab, String[] compatMods, String followItem) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    protected void assignTabs(BuildCreativeModeTabContentsEvent event) {
        for (ResourceKey<CreativeModeTab> tab : this.tabs) {
            if (event.getTabKey() == tab) {
                event.accept(this.item.toStack());
            }
        }
    }

    private List<ResourceKey<CreativeModeTab>> checkCompatibility(List<ResourceKey<CreativeModeTab>> tabs,
                                                                  String[] compatMods) {
        if (CompatUtil.areModsLoaded(compatMods)) {
            List<ResourceKey<CreativeModeTab>> compatibleTabs = new ArrayList<>(tabs);
            // TODO: how to hide from search tab?
            return compatibleTabs;
        }
        return Collections.emptyList();
    }

}
