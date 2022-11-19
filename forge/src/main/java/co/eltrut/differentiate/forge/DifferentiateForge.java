package co.eltrut.differentiate.forge;

import co.eltrut.differentiate.Differentiate;
import net.minecraftforge.fml.common.Mod;

@Mod(Differentiate.MOD_ID)
public class DifferentiateForge {
    public DifferentiateForge() {
        Differentiate.load();
    }
}