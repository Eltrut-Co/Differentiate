package co.eltrut.differentiate.core.recipe;

import java.util.Hashtable;

import com.google.gson.JsonObject;

import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class BooleanRecipeCondition implements ICondition {
	
public static final Hashtable<String, ConfigValue<Boolean>> TABLE = new Hashtable<>();
	
	private final ResourceLocation location;
	private final String condition;
	
	public BooleanRecipeCondition(ResourceLocation location, String condition) {
		this.location = location;
		this.condition = condition;
	}

	@Override
	public ResourceLocation getID() {
		return this.location;
	}

	@Override
	public boolean test() {
		return TABLE.containsKey(this.condition) ? TABLE.get(this.condition).get() : true;
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
