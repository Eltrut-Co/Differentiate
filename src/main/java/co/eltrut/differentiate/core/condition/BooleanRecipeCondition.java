package co.eltrut.differentiate.core.condition;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public record BooleanRecipeCondition(ResourceLocation location, String condition) implements ICondition {
	public static final Map<String, ConfigValue<Boolean>> MAP = new HashMap<>();

	@Override
	public ResourceLocation getID() {
		return this.location;
	}

	@Override
	public boolean test(IContext iContext) {
		return MAP.containsKey(this.condition) && MAP.get(this.condition).get();
	}

	public static class Serializer implements IConditionSerializer<BooleanRecipeCondition> {
		private final ResourceLocation location;

		public Serializer(String name) {
			this.location = new ResourceLocation(Differentiate.MOD_ID, name);
		}

		@Override
		public void write(JsonObject json, BooleanRecipeCondition value) {
			json.addProperty("condition", value.condition);
		}

		@Override
		public BooleanRecipeCondition read(JsonObject json) {
			return new BooleanRecipeCondition(this.location, json.getAsJsonPrimitive("condition").getAsString());
		}

		@Override
		public ResourceLocation getID() {
			return this.location;
		}
	}
}