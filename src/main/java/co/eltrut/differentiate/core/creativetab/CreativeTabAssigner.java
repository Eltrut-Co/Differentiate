package co.eltrut.differentiate.core.creativetab;

import co.eltrut.differentiate.core.util.CompatUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreativeTabAssigner {

    protected static final List<AbstractCreativeTabEntry> ITEMS = new ArrayList<>();

    public static void assignTabs(BuildCreativeModeTabContentsEvent event) {
        ITEMS.forEach(s -> s.assignTabs(event));
    }

    protected static List<ResourceKey<CreativeModeTab>> checkCompatibility(List<ResourceKey<CreativeModeTab>> tabs,
                                                                                       String[] compatMods) {
        if (CompatUtil.areModsLoaded(compatMods)) {
            // TODO: how to hide from search tab?
            return new ArrayList<>(tabs);
        }
        return Collections.emptyList();
    }

}
