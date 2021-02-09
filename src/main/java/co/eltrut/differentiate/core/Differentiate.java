package co.eltrut.differentiate.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.eltrut.differentiate.core.helper.Registrator;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("differentiate")
@Mod.EventBusSubscriber(modid = "differentiate", bus = Bus.MOD)
public class Differentiate
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "differentiate";
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
    public static Differentiate instance;

    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public Differentiate() {
        modEventBus.addListener(this::doCommonStuff);
        modEventBus.addListener(this::doClientStuff);
        instance = this;
        
        REGISTRATOR.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
    }
    
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
    	REGISTRATOR.getBlockHelper().registerBlockItems(event);
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    }

    private void doCommonStuff(final FMLCommonSetupEvent event)
    {
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
