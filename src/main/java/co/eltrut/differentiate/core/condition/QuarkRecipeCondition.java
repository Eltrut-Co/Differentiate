package co.eltrut.differentiate.core.condition;

import com.google.gson.JsonObject;

import co.eltrut.differentiate.core.Differentiate;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.CraftingHelper;

public class QuarkRecipeCondition implements ICondition {

	private final ResourceLocation location;
	private final String flag;
	
	public QuarkRecipeCondition(ResourceLocation location, String flag) {
		this.location = location;
		this.flag = flag;
	}

	@Override
	public boolean test(IContext iContext) {
		if (ModList.get().isLoaded("quark")) {
			JsonObject json = new JsonObject();
			json.addProperty("type", "quark:flag");
			json.addProperty("flag", this.flag);
			return true; // TODO: how does this work now
		}
		return false;
	}

	@Override
	public MapCodec<? extends ICondition> codec() {
		return null;
	}

//	public static class Serializer implements IConditionSerializer<QuarkRecipeCondition> {
//
//		private final ResourceLocation location;
//
//		public Serializer(String name) {
//			this.location = new ResourceLocation(Differentiate.MOD_ID, name);
//		}
//
//		@Override
//		public void write(JsonObject json, QuarkRecipeCondition value) {
//			json.addProperty("flag", value.flag);
//		}
//
//		@Override
//		public QuarkRecipeCondition read(JsonObject json) {
//			return new QuarkRecipeCondition(this.location, json.getAsJsonPrimitive("flag").getAsString());
//		}
//
//		@Override
//		public ResourceLocation getID() {
//			return this.location;
//		}
//
//	}

}
