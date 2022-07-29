package co.eltrut.differentiate.core.helper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class EntityHelper extends AbstractHelper<EntityType<?>> {

	private final DifferHelper parent;

	public EntityHelper(DifferHelper parent) {
		super(parent, ForgeRegistries.ENTITY_TYPES);
		this.parent = parent;
	}

	public RegistryObject<EntityType<?>> createEntity(String id, EntityType.EntityFactory<?> factory, MobCategory category, int width, int height, int trackingRange) {
		return this.deferredRegister.register(id, () ->
				EntityType.Builder.of(factory, category)
						.sized(width, height)
						.clientTrackingRange(trackingRange)
						.build(this.parent.getId()));
	}
}