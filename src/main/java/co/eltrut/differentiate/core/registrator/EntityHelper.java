package co.eltrut.differentiate.core.registrator;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHelper extends AbstractHelper<EntityType<?>> {

	public EntityHelper(DifferHelper parent) {
		super(parent, ForgeRegistries.ENTITIES);
	}
	
	public <E extends LivingEntity> RegistryObject<EntityType<E>> createSimpleEntity(String name, EntityFactory<E> factory, MobCategory classification, float width, float height) {
		return this.registry.register(name, () -> EntityType.Builder.of(factory, classification)
				.sized(width, height)
				.clientTrackingRange(64)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(3)
				.build((new ResourceLocation(this.parent.getModId(), name)).toString()));
	}

}
