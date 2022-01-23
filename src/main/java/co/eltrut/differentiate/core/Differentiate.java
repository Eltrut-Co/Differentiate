package co.eltrut.differentiate.core;

import co.eltrut.differentiate.core.condition.BooleanRecipeCondition;
import co.eltrut.differentiate.core.condition.QuarkRecipeCondition;
import co.eltrut.differentiate.core.registrator.Registrator;
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

@Mod("differentiate")
@Mod.EventBusSubscriber(modid = "differentiate", bus = Bus.MOD)
public class Differentiate {
    public static final String MOD_ID = "differentiate";
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
    public static Differentiate instance;

    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public Differentiate() {
        modEventBus.addListener(this::doCommonStuff);
        modEventBus.addListener(this::doClientStuff);
        instance = this;
        
        for (Registrator reg : Registrator.REGISTRATORS) reg.register(modEventBus);
        
        CraftingHelper.register(new BooleanRecipeCondition.Serializer("condition"));
        CraftingHelper.register(new QuarkRecipeCondition.Serializer("flag"));
        
        MinecraftForge.EVENT_BUS.register(this);
        
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    }
    
//    @SubscribeEvent
//    public static void onFuelRegister(FurnaceFuelBurnTimeEvent event) {
//    	ItemStack stack = event.getItemStack();
//    	for (RegistryObject<Item> item : ItemHelper.FUEL.keySet()) {
//    		if (item.get() == stack.getItem()) {
//    			event.setBurnTime(ItemHelper.FUEL.get(item));
//    		}
//    	}
//    }

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
