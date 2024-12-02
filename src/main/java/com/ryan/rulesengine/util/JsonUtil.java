package com.ryan.rulesengine.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryan.rulesengine.model.InputData;

public class JsonUtil {


    private static ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)  // 失败如果有未知属性
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true) ;

//    convert an object to JSON string
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

//    handle for InputData class: return default values instead of throwing exceptions
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (clazz == InputData.class) {
            try {
                JsonNode node = objectMapper.readTree(json);
                if (!node.has("id") || !node.has("numberOfChildren") ||
                        !node.has("familyComposition") || !node.has("familyUnitInPayForDecember")) {
                    throw new Exception("Missing required fields");
                }
                return objectMapper.readValue(json, clazz);
            } catch (Exception e) {
                InputData input = new InputData();
                input.setId("invalid-input");
                input.setNumberOfChildren(0);
                input.setFamilyComposition("invalid-input");
                input.setFamilyUnitInPayForDecember(false);
                return (T) input;
            }
        }
        // for other types: parse directly and throw exception if fails
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON", e);
        }
    }

}
