package co.eltrut.differentiate.common.capability;

import net.minecraft.nbt.CompoundTag;

public interface Capability {
    void writeToNbt(CompoundTag tag);

    void readFromNbt(CompoundTag tag);

    default CompoundTag toNBT() {
        CompoundTag nbt = new CompoundTag();
        this.writeToNbt(nbt);
        return nbt;
    }
}