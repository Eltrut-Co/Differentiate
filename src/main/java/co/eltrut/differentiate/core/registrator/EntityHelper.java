package co.eltrut.differentiate.core.registrator;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHelper extends AbstractHelper<EntityType<?>> {

	public EntityHelper(Registrator parent) {
		super(parent, ForgeRegistries.ENTITIES);
	}
	
	public <E extends LivingEntity> RegistryObject<EntityType<E>> createSimpleEntity(String name, EntityType.IFactory<E> factory, EntityClassification classification, float width, float height) {
		return this.registry.register(name, () -> EntityType.Builder.of(factory, classification)
				.sized(width, height)
				.clientTrackingRange(64)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(3)
				.build((new ResourceLocation(this.parent.getModId(), name)).toString()));
	}

}
