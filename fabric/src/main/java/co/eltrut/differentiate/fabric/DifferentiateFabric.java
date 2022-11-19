package co.eltrut.differentiate.fabric;

import co.eltrut.differentiate.Differentiate;
import net.fabricmc.api.ModInitializer;

public class DifferentiateFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Differentiate.load();
    }
}
