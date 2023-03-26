package co.eltrut.differentiate.fabric;

import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class DifferentiateEarlyRiser implements Runnable {
    protected static Map<String, CustomBoatType> BOATABLES = new HashMap<>();

    @Override
    public void run() {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String boat = remapper.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        String block = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_2248") + ';';

        EnumAdder enumBuilder = ClassTinkerers.enumBuilder(boat, block, "Ljava/lang/String;");

        for (CustomBoatType customBoatType : BOATABLES.values()) {
            enumBuilder.addEnum(customBoatType.getInternalName(), () -> new Object[]{customBoatType.getBaseBlock(), customBoatType.getName()});
        }
        System.out.println("BOATABLES contents: " + BOATABLES);

        enumBuilder.build();
    }

    public static void createBoatType(String id, Block block) {
        System.out.println("createBoatType called with id=" + id + " and block=" + block);
        BOATABLES.put(id.toUpperCase(), new CustomBoatType(id.toUpperCase(), block, id));
        System.out.println("BOATABLES contents: " + BOATABLES);
        System.out.println("BOATABLES size: " + BOATABLES.size()); // print the size of the map
    }
}
