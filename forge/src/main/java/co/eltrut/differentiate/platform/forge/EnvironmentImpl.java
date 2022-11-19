package co.eltrut.differentiate.platform.forge;

import co.eltrut.differentiate.platform.Environment;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

@MethodsReturnNonnullByDefault
public class EnvironmentImpl {
    public static CreativeModeTab createTab(ResourceLocation location, ItemStack icon) {
        return new CreativeModeTab(location.toString().replace(":", ".")) {
            @Override public ItemStack makeIcon() {
                return icon;
            }
        };
    }

    public static boolean isLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static Environment.Platform getPlatform() {
        return Environment.Platform.FORGE;
    }
}