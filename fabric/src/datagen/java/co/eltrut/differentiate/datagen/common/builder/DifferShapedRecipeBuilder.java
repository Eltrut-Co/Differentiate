package co.eltrut.differentiate.datagen.common.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class DifferShapedRecipeBuilder {
	private final Item result;
	private final int count;
	private final List<String> pattern = new ArrayList<>();
	private final Map<Character, Ingredient> key = new LinkedHashMap<>();
	private String group;
	private String[] mods;
	private String[] conditions;
	private String[] flags;

	public DifferShapedRecipeBuilder(ItemLike resultIn, int countIn) {
		this.result = resultIn.asItem();
		this.count = countIn;
	}

	public DifferShapedRecipeBuilder key(Character symbol, TagKey<Item> tagIn) {
		return this.key(symbol, Ingredient.of(tagIn));
	}

	public DifferShapedRecipeBuilder key(Character symbol, ItemLike itemIn) {
		return this.key(symbol, Ingredient.of(itemIn));
	}

	public DifferShapedRecipeBuilder key(Character symbol, Ingredient ingredientIn) {
		if (this.key.containsKey(symbol)) {
			throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
		} else if (symbol == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(symbol, ingredientIn);
			return this;
		}
	}

	public DifferShapedRecipeBuilder patternLine(String patternIn) {
		if (!this.pattern.isEmpty() && patternIn.length() != this.pattern.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.pattern.add(patternIn);
			return this;
		}
	}

	public DifferShapedRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}

	public DifferShapedRecipeBuilder addModCompat(String... mods) {
		this.mods = mods;
		return this;
	}

	public DifferShapedRecipeBuilder addConditions(String... conditions) {
		this.conditions = conditions;
		return this;
	}

	public DifferShapedRecipeBuilder addFlags(String... flags) {
		this.flags = flags;
		return this;
	}

	public void build(Consumer<FinishedRecipe> consumerIn) {
		ResourceLocation loc = Registry.ITEM.getKey(this.result);
		this.build(consumerIn, new ResourceLocation(loc.getNamespace(), "crafting/" + loc.getPath()));
	}

	public void build(Consumer<FinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
		if ((new ResourceLocation(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, new ResourceLocation(save));
		}
	}

	public void build(Consumer<FinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		consumerIn.accept(new Result(id, this.result, this.count,
				this.group == null ? "" : this.group, this.pattern, this.key,
				this.mods == null ? new String[0] : this.mods,
				this.conditions == null ? new String[0] : this.conditions,
				this.flags == null ? new String[0] : this.flags));
	}

	private void validate(ResourceLocation id) {
		if (this.pattern.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
		} else {
			Set<Character> set = new HashSet<>(this.key.keySet());
			set.remove(' ');

			for (String s : this.pattern) {
				for (int i = 0; i < s.length(); ++i) {
					char c0 = s.charAt(i);
					if (!this.key.containsKey(c0) && c0 != ' ') {
						throw new IllegalStateException(
								"Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
					}

					set.remove(c0);
				}
			}

			if (!set.isEmpty()) {
				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
			} else if (this.pattern.size() == 1 && this.pattern.get(0).length() == 1) {
				throw new IllegalStateException("Shaped recipe " + id
						+ " only takes in a single item - should it be a shapeless recipe instead?");
			}
		}
	}

	public class Result implements FinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		private final String[] mods;
		private final String[] conditions;
		private final String[] flags;

		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<String> patternIn,
				Map<Character, Ingredient> keyIn, String[] mods, String[] conditions, String[] flags) {
			this.id = idIn;
			this.result = resultIn;
			this.count = countIn;
			this.group = groupIn;
			this.pattern = patternIn;
			this.key = keyIn;
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

			for (String s : this.pattern) {
				jsonarray.add(s);
			}

			json.add("pattern", jsonarray);
			JsonObject jsonobject = new JsonObject();

			for (Entry<Character, Ingredient> entry : this.key.entrySet()) {
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			json.add("key", jsonobject);
			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}

			json.add("result", jsonobject1);

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
			return RecipeSerializer.SHAPED_RECIPE;
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