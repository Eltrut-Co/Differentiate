package co.eltrut.differentiate.core;

import co.eltrut.differentiate.core.condition.BooleanRecipeCondition;
import co.eltrut.differentiate.core.condition.QuarkRecipeCondition;
import co.eltrut.differentiate.core.creativetab.CreativeTabAssigner;
import co.eltrut.differentiate.core.registrator.Registrator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.crafting.CraftingHelper;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod("differentiate")
public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
    public static Differentiate instance;

    public Differentiate(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::doCommonStuff);
        modEventBus.addListener(this::doClientStuff);
        instance = this;

        modEventBus.addListener(this::loadCompleteEvent);
        modEventBus.addListener(this::buildContents);
        
//        CraftingHelper.register(new BooleanRecipeCondition.Serializer("condition"));
//        CraftingHelper.register(new QuarkRecipeCondition.Serializer("flag"));
        
    }

    private void loadCompleteEvent(FMLLoadCompleteEvent event) {
    }

    private void buildContents(BuildCreativeModeTabContentsEvent event) {
        CreativeTabAssigner.assignTabs(event);
    }

    private void doCommonStuff(final FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
    		Registrator.registerCommon(event);
    	});
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
    	event.enqueueWork(() -> {
    		Registrator.registerClient(event);
    	});
    }
}
