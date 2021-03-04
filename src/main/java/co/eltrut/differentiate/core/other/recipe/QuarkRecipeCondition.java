package co.eltrut.differentiate.core.other.recipe;

import com.google.gson.JsonObject;

import co.eltrut.differentiate.core.Differentiate;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

public class QuarkRecipeCondition implements ICondition {

	private final ResourceLocation location;
	private final String flag;
	
	public QuarkRecipeCondition(ResourceLocation location, String flag) {
		this.location = location;
		this.flag = flag;
	}
	
	@Override
	public ResourceLocation getID() {
		return this.location;
	}

	@Override
	public boolean test() {
		if (ModList.get().isLoaded("quark")) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "quark:flag");
			json.addProperty("flag", this.flag);
			return CraftingHelper.getCondition(json).test();
		}
		return false;
	}
	
	public static class Serializer implements IConditionSerializer<QuarkRecipeCondition> {
		
		private final ResourceLocation location;
		
		public Serializer(String name) {
			this.location = new ResourceLocation(Differentiate.MOD_ID, name);
		}
		
		@Override
		public void write(JsonObject json, QuarkRecipeCondition value) {
			json.addProperty("flag", value.flag);
		}

		@Override
		public QuarkRecipeCondition read(JsonObject json) {
			return new QuarkRecipeCondition(this.location, json.getAsJsonPrimitive("flag").getAsString());
		}

		@Override
		public ResourceLocation getID() {
			return this.location;
		}
		
	}

}
