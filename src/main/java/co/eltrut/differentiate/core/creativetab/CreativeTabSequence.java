package co.eltrut.differentiate.core.creativetab;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreativeTabSequence<T extends ItemLike> extends AbstractCreativeTabEntry {

    private List<DeferredHolder<T, T>> items;
    private final List<ResourceKey<CreativeModeTab>> tabs;
    private final ItemLike followItem;

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, List<ResourceKey<CreativeModeTab>> tabs,
                               String[] compatMods, ItemLike followItem) {
        this.items = items;
        this.tabs = CreativeTabAssigner.checkCompatibility(tabs, compatMods);
        this.followItem = followItem;

        CreativeTabAssigner.ITEMS.add(this);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, ResourceKey<CreativeModeTab> tab, String[] compatMods, ItemLike followItem) {
        this(items, new ArrayList<>(List.of(tab)), compatMods, followItem);
    }

    public CreativeTabSequence(List<DeferredHolder<T, T>> items, ResourceKey<CreativeModeTab> tab, String[] compatMods) {
        this(items, new ArrayList<>(List.of(tab)), compatMods, null);
    }

    @Override
    protected void assignTabs(BuildCreativeModeTabContentsEvent event) {
        List<DeferredHolder<T, T>> reversedItems = items.reversed();
        for (ResourceKey<CreativeModeTab> tab : this.tabs) {
            if (event.getTabKey() == tab) {
                if (this.followItem == null) {
                    this.items.stream().map(DeferredHolder::get).forEach(event::accept);
                } else {
                    reversedItems.stream().map(DeferredHolder::get).map(ItemStack::new).forEach(s ->
                    event.insertAfter(this.followItem.asItem().getDefaultInstance(), s,
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
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

}
