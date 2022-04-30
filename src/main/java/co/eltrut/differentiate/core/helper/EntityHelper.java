package co.eltrut.differentiate.core.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public record EntityHelper(DifferHelper<EntityType<?>> entityHelper) {
	public <E extends LivingEntity> RegistryObject<EntityType<?>> createSimpleEntity(String name, EntityFactory<E> factory, MobCategory classification, float width, float height) {
		return entityHelper.register(name, () -> EntityType.Builder.of(factory, classification)
				.sized(width, height)
				.clientTrackingRange(64)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(3)
				.build((new ResourceLocation(entityHelper.getModId(), name)).toString()));
	}

	public DifferHelper<EntityType<?>> getHelper() {
		return this.entityHelper;
	}
}