package co.eltrut.differentiate.core.datagen.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class DifferShapedRecipeBuilder {

	private final Item result;
	private final int count;
	private final List<String> pattern = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	private String group;
	private String[] mods;
	private String[] conditions;

	public DifferShapedRecipeBuilder(IItemProvider resultIn, int countIn) {
		this.result = resultIn.asItem();
		this.count = countIn;
	}

	public static DifferShapedRecipeBuilder shapedRecipe(IItemProvider resultIn) {
		return shapedRecipe(resultIn, 1);
	}

	public static DifferShapedRecipeBuilder shapedRecipe(IItemProvider resultIn, int countIn) {
		return new DifferShapedRecipeBuilder(resultIn, countIn);
	}

	public DifferShapedRecipeBuilder key(Character symbol, ITag<Item> tagIn) {
		return this.key(symbol, Ingredient.fromTag(tagIn));
	}

	public DifferShapedRecipeBuilder key(Character symbol, IItemProvider itemIn) {
		return this.key(symbol, Ingredient.fromItems(itemIn));
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

	public DifferShapedRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
		this.advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}

	public DifferShapedRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}
	
	public DifferShapedRecipeBuilder setModCompat(String ...mods) {
		this.mods = mods;
		return this;
	}
	
	public DifferShapedRecipeBuilder setConditions(String ...conditions) {
		this.conditions = conditions;
		return this;
	}

	public void build(Consumer<IFinishedRecipe> consumerIn) {
		ResourceLocation loc = ForgeRegistries.ITEMS.getKey(this.result);
		this.build(consumerIn, loc.getNamespace() + "/crafting/" + loc.getPath());
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
		ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(this.result);
		if ((new ResourceLocation(save)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
		} else {
			this.build(consumerIn, new ResourceLocation(save));
		}
	}

	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
		this.validate(id);
		this.advancementBuilder.withParentId(new ResourceLocation("recipes/root"))
				.withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id))
				.withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumerIn.accept(
				new DifferShapedRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group,
						this.pattern, this.key, this.advancementBuilder, new ResourceLocation(id.getNamespace(),
								"recipes/" + this.result.getGroup().getPath() + "/" + id.getPath()), 
						this.mods == null ? new String[0] : this.mods, this.conditions == null ? new String[0] : this.conditions));
	}

	private void validate(ResourceLocation id) {
		if (this.pattern.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
		} else {
			Set<Character> set = Sets.newHashSet(this.key.keySet());
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
			} else if (this.advancementBuilder.getCriteria().isEmpty()) {
				throw new IllegalStateException("No way of obtaining recipe " + id);
			}
		}
	}

	public class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final String[] mods;
		private final String[] conditions;

		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<String> patternIn,
				Map<Character, Ingredient> keyIn, Advancement.Builder advancementBuilderIn,
				ResourceLocation advancementIdIn, String[] mods, String[] conditions) {
			this.id = idIn;
			this.result = resultIn;
			this.count = countIn;
			this.group = groupIn;
			this.pattern = patternIn;
			this.key = keyIn;
			this.advancementBuilder = advancementBuilderIn;
			this.advancementId = advancementIdIn;
			this.mods = mods;
			this.conditions = conditions;
		}

		public void serialize(JsonObject json) {
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
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().serialize());
			}

			json.add("key", jsonobject);
			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}

			json.add("result", jsonobject1);
			
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
			json.add("conditions", jsonarray1);
		}

		public IRecipeSerializer<?> getSerializer() {
			return IRecipeSerializer.CRAFTING_SHAPED;
		}

		public ResourceLocation getID() {
			return this.id;
		}

		@Nullable
		public JsonObject getAdvancementJson() {
			return this.advancementBuilder.serialize();
		}

		@Nullable
		public ResourceLocation getAdvancementID() {
			return this.advancementId;
		}
	}

}
