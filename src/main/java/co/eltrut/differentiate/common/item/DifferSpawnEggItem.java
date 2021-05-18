package co.eltrut.differentiate.common.item;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

public class DifferSpawnEggItem extends SpawnEggItem {
	
	private final Supplier<EntityType<?>> defaultType;
	
	public DifferSpawnEggItem(Supplier<EntityType<?>> defaultType, int color1, int color2, Properties props) {
		super(null, color1, color2, props);
		this.defaultType = defaultType;
	}
	
	@Override
	public EntityType<?> getType(@Nullable CompoundNBT nbt) {
		if (nbt != null && nbt.contains("EntityTag", 10)) {
	         CompoundNBT compoundnbt = nbt.getCompound("EntityTag");
	         if (compoundnbt.contains("id", 8)) {
	            return EntityType.byString(compoundnbt.getString("id")).orElse(this.defaultType.get());
	         }
	      }
		
		return this.defaultType.get();
	}

}
