package co.eltrut.differentiate.core.creativetab;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreativeTabAssigner {

    public static final Map<String, List<CreativeModeTab>> ITEMS  = new HashMap<>();

    public static void assignTabs(BuildCreativeModeTabContentsEvent event) {
        for (String item : ITEMS.keySet()) {
            List<CreativeModeTab> tabs = ITEMS.get(item);
            for (CreativeModeTab tab : tabs) {
                if (event.getTab() == tab) {
//                    event.accept(item);
                }
            }
        }
    }

}
