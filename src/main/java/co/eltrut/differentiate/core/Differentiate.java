package co.eltrut.differentiate.core;

import co.eltrut.differentiate.core.condition.BooleanRecipeCondition;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * TODO: Use quark's vertical slabs, myalite things, and alt quark recipe conditions
 */
@Mod("differentiate")
@Mod.EventBusSubscriber(modid = "differentiate", bus = Bus.MOD)
public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static Differentiate instance;

    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public Differentiate() {
        modEventBus.addListener(this::doCommonStuff);
        modEventBus.addListener(this::doClientStuff);
        instance = this;

        CraftingHelper.register(new BooleanRecipeCondition.Serializer("condition"));
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    }

    private void doCommonStuff(final FMLCommonSetupEvent event) {
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}