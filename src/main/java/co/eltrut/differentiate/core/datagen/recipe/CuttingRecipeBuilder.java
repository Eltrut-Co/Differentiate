package co.eltrut.differentiate.core.datagen.recipe;

import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CuttingRecipeBuilder {

	private final Item result;
	private final Ingredient[] ingredients;
	private final int count;
	private String group;
	private final IRecipeSerializer<?> serializer;
	private String[] mods;
	private String[] conditions;
	private String[] flags;

	public CuttingRecipeBuilder(IRecipeSerializer<?> serializerIn, Ingredient[] ingredients, IItemProvider resultProviderIn, int countIn) {
	      this.serializer = serializerIn;
	      this.result = resultProviderIn.asItem();
	      this.ingredients = ingredients;
	      this.count = countIn;
	   }
	
	public CuttingRecipeBuilder addModCompat(String... mods) {
		this.mods = mods;
		return this;
	}

	public CuttingRecipeBuilder addConditions(String... conditions) {
		this.conditions = conditions;
		return this;
	}

	public CuttingRecipeBuilder addFlags(String... flags) {
		this.flags = flags;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
		if ((new ResourceLocation(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Single Item Recipe " + save + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, new ResourceLocation(save));
		}
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		consumerIn.accept(new CuttingRecipeBuilder.Result(id, this.serializer, this.group == null ? "" : this.group,
				this.ingredients, this.result, this.count, this.mods == null ? new String[0] : this.mods,
				this.conditions == null ? new String[0] : this.conditions,
				this.flags == null ? new String[0] : this.flags));
	}

	private void validate(ResourceLocation id) {
	}

	public static class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final String group;
		private final Ingredient[] ingredients;
		private final Item result;
		private final int count;
		private final IRecipeSerializer<?> serializer;
		private final String[] mods;
		private final String[] conditions;
		private final String[] flags;

		public Result(ResourceLocation idIn, IRecipeSerializer<?> serializerIn, String groupIn, Ingredient[] ingredients,
				Item resultIn, int countIn, String[] mods, String[] conditions, String[] flags) {
			this.id = idIn;
			this.serializer = serializerIn;
			this.group = groupIn;
			this.ingredients = ingredients;
			this.result = resultIn;
			this.count = countIn;
			this.mods = mods;
			this.conditions = conditions;
			this.flags = flags;
		}

		public void serialize(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			JsonArray temp1 = new JsonArray();
			for (Ingredient i : this.ingredients) {
				temp1.add(i.serialize());
			}
			json.add("ingredient", temp1);
			
			json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
			json.addProperty("count", this.count);
			
			if (this.conditions.length != 0 || this.mods.length != 0 || this.flags.length != 0) {
				JsonArray jsonarray1 = new JsonArray();
				for (String mod : mods) {
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "forge:mod_loaded");
					temp.addProperty("mod", mod);
					jsonarray1.add(temp);
				}
				for (String condition : conditions) {
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "differentiate:condition");
					temp.addProperty("condition", condition);
					jsonarray1.add(temp);
				}
				for (String flag : flags) {
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "differentiate:flag");
					temp.addProperty("flag", flag);
					jsonarray1.add(temp);
				}
				json.add("conditions", jsonarray1);
			}
		}

		public ResourceLocation getID() {
			return this.id;
		}

		public IRecipeSerializer<?> getSerializer() {
			return this.serializer;
		}

		@Override
		public JsonObject getAdvancementJson() {
			return null;
		}

		@Override
		public ResourceLocation getAdvancementID() {
			return null;
		}
	}

}
