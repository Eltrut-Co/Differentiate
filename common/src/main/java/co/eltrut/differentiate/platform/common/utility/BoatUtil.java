package co.eltrut.differentiate.platform.common.utility;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class BoatUtil {
    public static List<CustomBoatType> BOATS = new ArrayList<>();

//    static {
//        BOATS.add(new CustomBoatType("CALCITE", Blocks.CALCITE, "calcite"));
//    }

    public static void addCustomBoatType(CustomBoatType type) {
        BOATS.add(type);
    }
}
