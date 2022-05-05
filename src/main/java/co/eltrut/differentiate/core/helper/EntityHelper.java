package co.eltrut.differentiate.core.helper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

public record EntityHelper(DifferHelper<EntityType<?>> entityHelper) {
	public RegistryObject<EntityType<?>> createEntity(String id, EntityType.EntityFactory<?> factory, MobCategory category, int width, int height, int trackingRange) {
		return entityHelper.register(id, () ->
				EntityType.Builder.of(factory, category)
						.sized(width, height)
						.clientTrackingRange(trackingRange)
						.build(entityHelper.getId()));
	}
}