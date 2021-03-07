package co.eltrut.differentiate.core.datagen.recipe;

import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class DifferCookingRecipeBuilder {

	private final Item result;
	private final Ingredient ingredient;
	private final float experience;
	private final int cookingTime;
	private String group;
	private final CookingRecipeSerializer<?> recipeSerializer;
	private String[] mods;
	private String[] conditions;
	private String[] flags;

	public DifferCookingRecipeBuilder(IItemProvider resultIn, Ingredient ingredientIn, float experienceIn, int cookingTimeIn, CookingRecipeSerializer<?> serializer) {
	      this.result = resultIn.asItem();
	      this.ingredient = ingredientIn;
	      this.experience = experienceIn;
	      this.cookingTime = cookingTimeIn;
	      this.recipeSerializer = serializer;
	   }
	
	public DifferCookingRecipeBuilder addModCompat(String... mods) {
		this.mods = mods;
		return this;
	}

	public DifferCookingRecipeBuilder addConditions(String... conditions) {
		this.conditions = conditions;
		return this;
	}

	public DifferCookingRecipeBuilder addFlags(String... flags) {
		this.flags = flags;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumerIn) {
		this.build(consumerIn, ForgeRegistries.ITEMS.getKey(this.result));
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
		ResourceLocation resourcelocation1 = new ResourceLocation(save);
		if (resourcelocation1.equals(resourcelocation)) {
			throw new IllegalStateException("Recipe " + resourcelocation1 + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, resourcelocation1);
		}
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		consumerIn.accept(new DifferCookingRecipeBuilder.Result(id, this.group == null ? "" : this.group, this.ingredient,
						this.result, this.experience, this.cookingTime, this.recipeSerializer, this.mods == null ? new String[0] : this.mods,
						this.conditions == null ? new String[0] : this.conditions,
						this.flags == null ? new String[0] : this.flags));
	}

	private void validate(ResourceLocation id) {
	}

	public static class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final String group;
		private final Ingredient ingredient;
		private final Item result;
		private final float experience;
		private final int cookingTime;
		private final IRecipeSerializer<? extends AbstractCookingRecipe> serializer;
		private final String[] mods;
		private final String[] conditions;
		private final String[] flags;

		public Result(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, Item resultIn, float experienceIn,
				int cookingTimeIn, IRecipeSerializer<? extends AbstractCookingRecipe> serializerIn, String[] mods,
				String[] conditions, String[] flags) {
			this.id = idIn;
			this.group = groupIn;
			this.ingredient = ingredientIn;
			this.result = resultIn;
			this.experience = experienceIn;
			this.cookingTime = cookingTimeIn;
			this.serializer = serializerIn;
			this.mods = mods;
			this.conditions = conditions;
			this.flags = flags;
		}

		public void serialize(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			json.add("ingredient", this.ingredient.serialize());
			json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
			json.addProperty("experience", this.experience);
			json.addProperty("cookingtime", this.cookingTime);
			
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

		public IRecipeSerializer<?> getSerializer() {
			return this.serializer;
		}

		public ResourceLocation getID() {
			return this.id;
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
