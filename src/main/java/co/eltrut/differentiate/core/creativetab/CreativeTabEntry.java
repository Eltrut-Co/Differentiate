package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.ItemUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

public class CreativeTabEntry extends AbstractCreativeTabEntry {

    private final DeferredItem<? extends Item> item;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final ItemLike followItem;
    private final Pair<String, String> followItemId;

    public CreativeTabEntry(DeferredItem<? extends Item> item, List<ResourceKey<CreativeModeTab>> tabs, String[] compatMods, ItemLike followItem) {
        this.item = item;
        this.followItem = followItem;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);
        this.followItemId = null;

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, ResourceKey<CreativeModeTab> tab, String[] compatMods, ItemLike followItem) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, ResourceKey<CreativeModeTab> tab, String[] compatMods) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, null);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, List<ResourceKey<CreativeModeTab>> tabs, String[] compatMods,
                            String modid, String followItem) {
        this.item = item;
        this.followItem = null;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);
        this.followItemId = Pair.of(modid, followItem);

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabEntry(DeferredItem<? extends Item> item, ResourceKey<CreativeModeTab> tab, String[] compatMods,
                            String modid, String followItem) {
        this(item, new ArrayList<>(List.of(tab)), compatMods, modid, followItem);
    }

    @Override
    public void assignTabs(BuildCreativeModeTabContentsEvent event) {
        for (ResourceKey<CreativeModeTab> tab : this.tabs) {
            if (event.getTabKey() == tab) {
                try {
                    if (this.followItem != null) {
                        event.insertAfter(this.followItem.asItem().getDefaultInstance(), this.item.toStack(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    }
                    else if (this.followItemId != null) {
                        Item item = CompatUtil.getItem(this.followItemId.getLeft(), this.followItemId.getRight());
                        if (item != null) {
                            event.insertAfter(item.getDefaultInstance(), this.item.toStack(),
                                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        }
                    } else {
                        event.accept(this.item.toStack());
                    }
                }
                catch (IllegalArgumentException e) {
                    Differentiate.LOGGER.warn("Unable to load {} into its creative tab(s)", ItemUtil.getIdFromItem(this.item.asItem()));
                }
            }
        }
    }

}
