package co.eltrut.differentiate.core.registrator;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityHelper extends AbstractHelper<EntityType<?>, DeferredRegister<EntityType<?>>> {

	public EntityHelper(Registrator parent) {
		super(parent, DeferredRegister.create(Registries.ENTITY_TYPE, parent.getModId()));
	}
	
//	public <E extends LivingEntity> DeferredHolder<Entity, E> createSimpleEntity(String name, EntityFactory<E> factory, MobCategory classification, float width, float height) {
//		return this.registry.register(name, () -> EntityType.Builder.of(factory, classification)
//				.sized(width, height)
//				.clientTrackingRange(64)
//				.setShouldReceiveVelocityUpdates(true)
//				.setUpdateInterval(3)
//				.build((ResourceLocation.fromNamespaceAndPath(this.parent.getModId(), name)).toString()));
//	}

}
