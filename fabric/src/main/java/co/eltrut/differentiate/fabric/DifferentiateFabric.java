package co.eltrut.differentiate.fabric;

import co.eltrut.differentiate.Differentiate;
import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.resource.loader.FabricLifecycledResourceManager;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.LogManager;

public class DifferentiateFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DifferentiateEarlyRiser.createBoatType("basalt", Blocks.BASALT);

        Differentiate.load();

//        FabricLifecycleEvents.INITIALIZATION.register(this::initialize);
//        Boat.Type type = ClassTinkerers.getEnum(Boat.Type.class, "BASALT");
//        LogManager.getLogger().info("Boat Type: " + type + " with ordinal " + type.ordinal());
    }
}
