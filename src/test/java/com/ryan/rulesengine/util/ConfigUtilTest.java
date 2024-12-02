package com.ryan.rulesengine.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfigUtilTest {

    @Test
    public void testGetProp() {
        String inputBase = ConfigUtil.getProp("mqtt.input.base");
        String outputBase = ConfigUtil.getProp("mqtt.output.base");
        String url = ConfigUtil.getProp("mqtt.url");
        String topicId = ConfigUtil.getProp("topicId");
        String clientIdBase = ConfigUtil.getProp("mqtt.client.id.base");


        Assertions.assertNotNull(inputBase);
        Assertions.assertNotNull(outputBase);
        Assertions.assertNotNull(url);
        Assertions.assertNotNull(clientIdBase);

    }

    @Test
    public void testGetNonExistentProp() {
        String notExisted = ConfigUtil.getProp("not.existed.ihuuhfashfd2398429084371!@#$%^&*()_+{}:");
        Assertions.assertNull(notExisted);
    }

}
