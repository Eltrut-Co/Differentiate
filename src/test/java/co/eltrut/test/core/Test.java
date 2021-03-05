package co.eltrut.test.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.eltrut.differentiate.core.registrator.Registrator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("test")
@Mod.EventBusSubscriber(modid = "test", bus = Bus.MOD)
public class Test {
	private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "test";
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
	public static Test instance;

	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

	public Test() {
	    modEventBus.addListener(this::doCommonStuff);
	    modEventBus.addListener(this::doClientStuff);
	    instance = this;
	    
	    MinecraftForge.EVENT_BUS.register(this);
	       
	}
	
	public void doCommonStuff(final FMLCommonSetupEvent event) {}
	
	public void doClientStuff(final FMLClientSetupEvent event) {}
	    
}