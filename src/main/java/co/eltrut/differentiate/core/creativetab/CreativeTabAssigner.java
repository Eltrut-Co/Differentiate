package co.eltrut.differentiate.core.creativetab;

import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

public class CreativeTabAssigner {

    protected static final List<CreativeTabEntry> ITEMS = new ArrayList<>();

    public static void assignTabs(BuildCreativeModeTabContentsEvent event) {
        ITEMS.forEach(s -> s.assignTabs(event));
    }

}
