package co.eltrut.differentiate.core.creativetab;

import net.minecraft.world.item.CreativeModeTab;

import java.util.List;

public class CreativeTabEntry {

    private final List<CreativeModeTab> tabs;
    private final List<String> compatMods;
    private final String followItem;

    public CreativeTabEntry(List<CreativeModeTab> tabs, List<String> compatMods, String followItem) {
        this.tabs = tabs;
        this.compatMods = compatMods;
        this.followItem = followItem;
    }


}
