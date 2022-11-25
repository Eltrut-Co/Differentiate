package co.eltrut.differentiate.datagen.common.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class DifferCookingRecipeBuilder {
	private final Item result;
	private final Ingredient ingredient;
	private final float experience;
	private final int cookingTime;
	private String group;
	private final SimpleCookingSerializer<?> recipeSerializer;
	private String[] mods;
	private String[] conditions;
	private String[] flags;

	public DifferCookingRecipeBuilder(ItemLike resultIn, Ingredient ingredientIn, float experienceIn, int cookingTimeIn, SimpleCookingSerializer<?> serializer) {
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

	public void build(Consumer<FinishedRecipe> consumerIn) {
		this.build(consumerIn, Registry.ITEM.getKey(this.result));
	}

	public void build(Consumer<FinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
		ResourceLocation resourcelocation1 = new ResourceLocation(save);
		if (resourcelocation1.equals(resourcelocation)) {
			throw new IllegalStateException("Recipe " + resourcelocation1 + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, resourcelocation1);
		}
	}

	public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		consumerIn.accept(new Result(id, this.group == null ? "" : this.group, this.ingredient,
						this.result, this.experience, this.cookingTime, this.recipeSerializer, this.mods == null ? new String[0] : this.mods,
						this.conditions == null ? new String[0] : this.conditions,
						this.flags == null ? new String[0] : this.flags));
	}

	private void validate(ResourceLocation id) {
	}

	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final String group;
		private final Ingredient ingredient;
		private final Item result;
		private final float experience;
		private final int cookingTime;
		private final RecipeSerializer<? extends AbstractCookingRecipe> serializer;
		private final String[] mods;
		private final String[] conditions;
		private final String[] flags;

		public Result(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, Item resultIn, float experienceIn,
				int cookingTimeIn, RecipeSerializer<? extends AbstractCookingRecipe> serializerIn, String[] mods,
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

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			json.add("ingredient", this.ingredient.toJson());
			json.addProperty("result", Registry.ITEM.getKey(this.result).toString());
			json.addProperty("experience", this.experience);
			json.addProperty("cookingtime", this.cookingTime);
			
			if (this.conditions.length != 0 || this.mods.length != 0 || this.flags.length != 0) {
				JsonArray jsonarray1 = new JsonArray();
				for (String mod : mods) {
					if (mod == null) continue;
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "forge:mod_loaded");
					temp.addProperty("modid", mod);
					jsonarray1.add(temp);
				}
				for (String condition : conditions) {
					if (condition == null) continue;
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "differentiate:condition");
					temp.addProperty("condition", condition);
					jsonarray1.add(temp);
				}
				for (String flag : flags) {
					if (flag == null) continue;
					JsonObject temp = new JsonObject();
					temp.addProperty("type", "differentiate:flag");
					temp.addProperty("flag", flag);
					jsonarray1.add(temp);
				}
				json.add("conditions", jsonarray1);
			}
		}

		@Override
		public RecipeSerializer<?> getType() {
			return this.serializer;
		}

		@Override
		public ResourceLocation getId() {
			return this.id;
		}

		@Override
		public JsonObject serializeAdvancement() {
			return null;
		}

		@Override
		public ResourceLocation getAdvancementId() {
			return null;
		}
	}
}