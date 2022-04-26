package co.eltrut.differentiate.core.datagen.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DifferShapelessRecipeBuilder {

	private final Item result;
	private final int count;
	private final List<Ingredient> ingredients = new ArrayList<>();
	private String group;
	private String[] mods;
	private String[] conditions;
	private String[] flags;

	public DifferShapelessRecipeBuilder(ItemLike resultIn, int countIn) {
	      this.result = resultIn.asItem();
	      this.count = countIn;
	   }

	public DifferShapelessRecipeBuilder addIngredient(TagKey<Item> tagIn) {
		return this.addIngredient(Ingredient.of(tagIn));
	}

	public DifferShapelessRecipeBuilder addIngredient(ItemLike itemIn) {
		return this.addIngredient(itemIn, 1);
	}

	public DifferShapelessRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
		for (int i = 0; i < quantity; ++i) {
			this.addIngredient(Ingredient.of(itemIn));
		}

		return this;
	}

	public DifferShapelessRecipeBuilder addIngredient(Ingredient ingredientIn) {
		return this.addIngredient(ingredientIn, 1);
	}

	public DifferShapelessRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
		for (int i = 0; i < quantity; ++i) {
			this.ingredients.add(ingredientIn);
		}

		return this;
	}

	public DifferShapelessRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}
	
	public DifferShapelessRecipeBuilder addModCompat(String... mods) {
		this.mods = mods;
		return this;
	}

	public DifferShapelessRecipeBuilder addConditions(String... conditions) {
		this.conditions = conditions;
		return this;
	}

	public DifferShapelessRecipeBuilder addFlags(String... flags) {
		this.flags = flags;
		return this;
	}

	public void build(Consumer<FinishedRecipe> consumerIn) {
		ResourceLocation loc = ForgeRegistries.ITEMS.getKey(this.result);
		this.build(consumerIn, new ResourceLocation(loc.getNamespace(), "crafting/" + loc.getPath()));
	}

	public void build(Consumer<FinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
		if ((new ResourceLocation(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Shapeless Recipe " + save + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, new ResourceLocation(save));
		}
	}

	public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		consumerIn.accept(new DifferShapelessRecipeBuilder.Result(id, this.result, this.count,
				this.group == null ? "" : this.group, this.ingredients,
				this.mods == null ? new String[0] : this.mods,
				this.conditions == null ? new String[0] : this.conditions,
				this.flags == null ? new String[0] : this.flags));
	}

	private void validate(ResourceLocation id) {
	}

	public static class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<Ingredient> ingredients;
		private final String[] mods;
		private final String[] conditions;
		private final String[] flags;

		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<Ingredient> ingredientsIn,
				String[] mods, String[] conditions, String[] flags) {
			this.id = idIn;
			this.result = resultIn;
			this.count = countIn;
			this.group = groupIn;
			this.ingredients = ingredientsIn;
			this.mods = mods;
			this.conditions = conditions;
			this.flags = flags;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}

			JsonArray jsonarray = new JsonArray();

			for (Ingredient ingredient : this.ingredients) {
				jsonarray.add(ingredient.toJson());
			}

			json.add("ingredients", jsonarray);
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject.addProperty("count", this.count);
			}

			json.add("result", jsonobject);
			
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
			return RecipeSerializer.SHAPELESS_RECIPE;
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
