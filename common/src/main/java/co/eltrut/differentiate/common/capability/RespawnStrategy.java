package co.eltrut.differentiate.common.capability;

import net.minecraft.nbt.CompoundTag;

public abstract class RespawnStrategy {
    public static final RespawnStrategy ALWAYS_COPY = new RespawnStrategy() {
        @Override
        public void copy(Capability capability, Capability newCapability, boolean returnedFromEnd, boolean keepInventory) {
            this.copyWithNBT(capability, newCapability);
        }
    };

    public static final RespawnStrategy INVENTORY = new RespawnStrategy() {
        @Override
        public void copy(Capability capability, Capability newCapability, boolean returnedFromEnd, boolean keepInventory) {
            if (returnedFromEnd || keepInventory) {
                this.copyWithNBT(capability, newCapability);
            }
        }
    };

    public static final RespawnStrategy LOSSLESS = new RespawnStrategy() {
        @Override
        public void copy(Capability capability, Capability newCapability, boolean returnedFromEnd, boolean keepInventory) {
            if (returnedFromEnd) {
                this.copyWithNBT(capability, newCapability);
            }
        }
    };

    public static final RespawnStrategy NEVER = new RespawnStrategy() {
        @Override
        public void copy(Capability capability, Capability newCapability, boolean returnedFromEnd, boolean keepInventory) {}
    };

    protected void copyWithNBT(Capability capability, Capability newCapability) {
        CompoundTag nbt = new CompoundTag();
        capability.writeToNbt(nbt);
        newCapability.readFromNbt(nbt);
    }

    public abstract void copy(Capability capability, Capability newCapability, boolean returnedFromEnd, boolean keepInventory);
}