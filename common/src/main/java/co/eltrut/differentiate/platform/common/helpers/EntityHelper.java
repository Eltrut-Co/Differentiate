package co.eltrut.differentiate.platform.common.helpers;

import co.eltrut.differentiate.platform.Helper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EntityHelper extends Helper<EntityType<?>> {
	private final Helper<EntityType<?>> helper;

	public EntityHelper(Helper<EntityType<?>> helper) {
		super(helper.registry, helper.modId);
		this.helper = helper;
	}

	public Supplier<EntityType<?>> createEntity(String id, EntityType.EntityFactory<?> factory, MobCategory category, int width, int height, int trackingRange) {
		return this.helper.register(id, () ->
				EntityType.Builder.of(factory, category)
						.sized(width, height)
						.clientTrackingRange(trackingRange)
						.build(this.helper.modId));
	}

	@Override
	public <E extends EntityType<?>> Supplier<E> register(String key, Supplier<E> entry) {
		return helper.register(key, entry);
	}

	@Override
	public void load() {
		helper.load();
	}
}