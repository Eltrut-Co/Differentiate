package co.eltrut.differentiate.forge;

import co.eltrut.differentiate.Differentiate;
import co.eltrut.differentiate.core.mixin.forge.BoatTypeMixin;
import co.eltrut.differentiate.platform.common.utility.BoatUtil;
import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;

@Mod(Differentiate.MOD_ID)
public class DifferentiateForge {
    public DifferentiateForge() {

        Differentiate.load();
    }

    static {
        BoatUtil.addCustomBoatType(new CustomBoatType("BASALT", Blocks.BASALT, "basalt"));
    }

}