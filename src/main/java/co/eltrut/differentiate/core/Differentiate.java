package co.eltrut.differentiate.core;

import co.eltrut.differentiate.core.creativetab.CreativeTabAssigner;
import co.eltrut.differentiate.core.registrator.Registrator;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod("differentiate")
public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
    public static Differentiate instance;

    public Differentiate(IEventBus modEventBus, ModContainer modContainer) {
        instance = this;

        modEventBus.addListener(this::buildContents);

        // comment out before shipping
//        REGISTRATOR.register(modEventBus);
        
    }

    private void buildContents(BuildCreativeModeTabContentsEvent event) {
        CreativeTabAssigner.assignTabs(event);
    }
}
