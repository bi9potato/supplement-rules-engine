package com.ryan.rulesengine.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ryan.rulesengine.model.InputData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonUtilTest {

    @Test
    public void testToJson() throws JsonProcessingException {
        InputData inputData = new InputData();
        inputData.setId("testId");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(2);
        inputData.setFamilyUnitInPayForDecember(true);

        String json = JsonUtil.toJson(inputData);

        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("\"id\":\"testId\""));
    }

    @Test
    public void testFromJson() throws JsonProcessingException {
        String json = "{\"id\":\"ryan1015\",\"numberOfChildren\":3,\"familyComposition\":\"couple\",\"familyUnitInPayForDecember\":true}";

        InputData inputData = JsonUtil.fromJson(json, InputData.class);

        Assertions.assertEquals("ryan1015", inputData.getId());
        Assertions.assertEquals("couple", inputData.getFamilyComposition());
        Assertions.assertEquals(3, inputData.getNumberOfChildren());
        Assertions.assertTrue(inputData.isFamilyUnitInPayForDecember());
    }

    @Test
    public void testInvalidJson() {
        String invalidJson = "{invalid json}";

        Assertions.assertThrows(JsonProcessingException.class, () -> {
            JsonUtil.fromJson(invalidJson, InputData.class);
        });
    }


}
