package com.airhacks;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.stream.JsonCollectors;

/**
 *
 * @author ratcashdev
 */
public class ExtendedJsonPatchGenerator {
	public static final String OLD_VALUE = "oldValue";
	
	public static JsonArray createDiff(final JsonStructure source, JsonStructure target) {
		JsonPatch diff = Json.createDiff(source, target);
		// now add the old values for replace operations
		JsonArray enhancedPatchArray = diff.toJsonArray().stream()
				.map(f -> addOldValue(f, source)).collect(JsonCollectors.toJsonArray());
		return enhancedPatchArray;
	}
	
	static JsonStructure addOldValue(JsonValue value, JsonStructure source) {
		JsonObject jsonObj = value.asJsonObject();
		JsonObjectBuilder enhancedObj = Json.createObjectBuilder(jsonObj);
		if (jsonObj.getJsonString("op").getString().equalsIgnoreCase(JsonPatch.Operation.REPLACE.operationName())) {
			JsonValue oldValue = source.getValue(jsonObj.getJsonString("path").getString());
			enhancedObj.add(OLD_VALUE, oldValue);
		}
		return enhancedObj.build();
	}
	
	/**
	 * must contain only replace operators, that means structure is identical
	 * @param diff
	 * @return true if only replace operations are contained
	 */
	public static boolean isValueChangeOnly(JsonArray diff) {
		List<JsonValue> replaceOps = diff.stream().filter(ExtendedJsonPatchGenerator::allowOnlyReplaceWithSameValueType)
				.collect(Collectors.toList());
		
		return replaceOps.size() == diff.size();
	}
	
	static boolean allowOnlyReplaceWithSameValueType(JsonValue value) {
		JsonObject jsonObj = value.asJsonObject();
		Objects.nonNull(jsonObj.getJsonString(OLD_VALUE));
		return jsonObj.getJsonString("op").getString().equalsIgnoreCase(JsonPatch.Operation.REPLACE.operationName()) &&
				jsonObj.get(OLD_VALUE).getValueType() == jsonObj.get("value").getValueType();
	}
}
