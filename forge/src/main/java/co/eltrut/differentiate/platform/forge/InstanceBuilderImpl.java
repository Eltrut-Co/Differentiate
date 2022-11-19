package co.eltrut.differentiate.platform.forge;

import co.eltrut.differentiate.platform.Instance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class InstanceBuilderImpl {
    public static Instance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Instance(modId, common, postCommon, client, postClient) {
            @Override
            public void load() {
                IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
                bus.<FMLCommonSetupEvent>addListener(event -> this.onPostCommon.run());
                bus.<FMLClientSetupEvent>addListener(event -> this.onPostClient.run());
                this.onCommon.run();
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> this.onClient.run());
            }
        };
    }
}