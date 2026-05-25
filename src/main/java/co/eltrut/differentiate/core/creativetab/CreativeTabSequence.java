package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.util.CompatUtil;
import co.eltrut.differentiate.core.util.ItemUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreativeTabSequence<T extends ItemLike> extends AbstractCreativeTabEntry {

    private List<DeferredHolder<T, T>> items;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final ItemLike followItem;
    private final Pair<String, String> followItemId;

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, List<ResourceKey<CreativeModeTab>> tabs,
                               String[] compatMods, ItemLike followItem) {
        this.items = items;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);
        this.followItem = followItem;
        this.followItemId = null;

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, ResourceKey<CreativeModeTab> tab, String[] compatMods, ItemLike followItem) {
        this(items, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, ResourceKey<CreativeModeTab> tab, String[] compatMods) {
        this(items, new ArrayList<>(List.of(tab)), compatMods, null);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, List<ResourceKey<CreativeModeTab>> tabs,
                               String[] compatMods, String modid, String followItem) {
        this.items = items;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);
        this.followItem = null;
        this.followItemId = Pair.of(modid, followItem);

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, ResourceKey<CreativeModeTab> tab, String[] compatMods,
                               String modid, String followItem) {
        this(items, new ArrayList<>(List.of(tab)), compatMods, modid, followItem);
    }

    @Override
    protected void assignTabs(BuildCreativeModeTabContentsEvent event) {
        List<DeferredHolder<T, T>> reversedItems = items.reversed();
        for (ResourceKey<CreativeModeTab> tab : this.tabs) {
            if (event.getTabKey() == tab) {
                try {
                    if (this.followItem != null) {
                        reversedItems.stream().map(DeferredHolder::get).map(ItemStack::new).forEach(s ->
                                event.insertAfter(this.followItem.asItem().getDefaultInstance(), s,
                                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
                    } else if (this.followItemId != null) {
                        Item item = CompatUtil.getItem(this.followItemId.getLeft(), this.followItemId.getRight());
                        if (item != null) {
                            reversedItems.stream().map(DeferredHolder::get).map(ItemStack::new).forEach(s ->
                                    event.insertAfter(item.getDefaultInstance(), s,
                                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
                        }
                    } else {
                        this.items.stream().map(DeferredHolder::get).forEach(event::accept);
                    }
                }
                catch (IllegalArgumentException e) {
                    Differentiate.LOGGER.warn("Unable to load {} into its creative tab(s)", ItemUtil.getIdFromItem(this.items.getFirst().get().asItem()));
                }
            }
        }
    }

    public void sortAlphabetically() {
        Map<String, DeferredHolder<T, T>> map = new HashMap<>();

        for (DeferredHolder<T, T> deferredItem : this.items) {
            Item item = deferredItem.get().asItem();
            String name = BuiltInRegistries.ITEM.getKey(item).getPath();
            map.put(name, deferredItem);
        }

        this.items = map.keySet().stream().sorted().map(map::get).collect(Collectors.toList());

    }

    public void add(DeferredHolder<T, T> deferredItem) {
        this.items.add(deferredItem);
    }

}
