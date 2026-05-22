package co.eltrut.differentiate.core.condition;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.*;
import net.minecraft.util.GsonHelper;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.Map;
import java.util.stream.Stream;

public class ConfigCondition implements ICondition {

	private final MapCodec<ConfigCondition> codec;
	private final String condition;
	private final ModConfigSpec.ConfigValue<Boolean> value;
	
	public ConfigCondition(MapCodec<ConfigCondition> codec, String condition, ModConfigSpec.ConfigValue<Boolean> value) {
		this.condition = condition;
		this.codec = codec;
		this.value = value;
	}

	@Override
	public boolean test(IContext iContext) {
		return this.value.get();
	}

	@Override
	public MapCodec<? extends ICondition> codec() {
		return this.codec;
	}

	public static class Serializer extends MapCodec<ConfigCondition> {

		private final Map<String, ModConfigSpec.ConfigValue<Boolean>> values;

		public Serializer(Map<String, ModConfigSpec.ConfigValue<Boolean>> values) {
            this.values = values;
		}

		@Override
		public <T> Stream<T> keys(DynamicOps<T> ops) {
			return Stream.of(ops.createString("condition"));
		}

		@Override
		public <T> DataResult<ConfigCondition> decode(DynamicOps<T> ops, MapLike<T> input) {
			JsonElement element = ops.convertTo(JsonOps.INSTANCE, ops.createMap(input.entries()));
			if (!(element instanceof JsonObject json))
				return DataResult.error(() -> "Expected an object");
			if (!json.has("value"))
				return DataResult.error(() -> "Missing 'value'");
			String name = GsonHelper.getAsString(json, "value");
			ModConfigSpec.ConfigValue<Boolean> configValue = this.values.get(name);
			if (configValue == null)
				return DataResult.error(() -> "No config value with name '" + name + "' found");

			return DataResult.success(new ConfigCondition(this, name, configValue));
		}

		@Override
		public <T> RecordBuilder<T> encode(ConfigCondition input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
			prefix.add("condition", ops.createString(input.condition));
			return prefix;
		}
	}

}
