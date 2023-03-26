package co.eltrut.differentiate.core.mixin.forge;

import co.eltrut.differentiate.platform.common.utility.BoatUtil;
import co.eltrut.differentiate.platform.common.utility.CustomBoatType;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
@Mixin(Boat.Type.class)
public class BoatTypeMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Boat.Type newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError("Mixin injection failed - BoatTypeMixin.");
    }

    @SuppressWarnings("ShadowTarget")
    @Final
    @Shadow
    @Mutable
    private static Boat.Type[] $VALUES;

    protected static ArrayList<CustomBoatType> BOATABLES;

    static {
        BOATABLES.addAll(BoatUtil.BOATS);
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;$VALUES:[Lnet/minecraft/world/entity/vehicle/Boat$Type;",
            shift = At.Shift.AFTER))
    private static void addCustomBoatTypes(CallbackInfo ci) {
        // Call a static method from another class to get the custom boat types
        var customBoatTypes = BOATABLES;

        var types = new ArrayList<>(Arrays.asList($VALUES));
        var last = types.get(types.size() - 1);

        for (CustomBoatType customBoatType : customBoatTypes) {
            var newType = newType(customBoatType.getInternalName(), last.ordinal() + 1, customBoatType.getBaseBlock(), customBoatType.getName());
            types.add(newType);
            customBoatType.setType(newType);
        }

        $VALUES = types.toArray(new Boat.Type[0]);

        // Print out the custom boat types in the BoatUtil.BOATS list
        System.out.println("BoatUtil.BOATS contents: " + BOATABLES);
    }


}
