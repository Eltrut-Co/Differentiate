package co.eltrut.differentiate.platform.common.utility;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;

public class CustomBoatType {
    private final String internalName;
    private final Block baseBlock;
    private final String name;
    private Boat.Type type;

    public CustomBoatType(String internalName, Block baseBlock, String name) {
        this.internalName = internalName;
        this.baseBlock = baseBlock;
        this.name = name;
    }

    public String getInternalName() {
        return internalName;
    }

    public Block getBaseBlock() {
        return baseBlock;
    }

    public String getName() {
        return name;
    }

    public void setType(Boat.Type type) {
        this.type = type;
    }

    public Boat.Type getType() {
        return type;
    }
}