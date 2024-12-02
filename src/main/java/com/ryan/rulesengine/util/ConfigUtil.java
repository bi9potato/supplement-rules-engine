package com.ryan.rulesengine.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Properties prop = new Properties();

//    static block to load properties
    static {
        ClassLoader classLoader =ConfigUtil.class.getClassLoader();

        try (InputStream config = classLoader.getResourceAsStream("config.properties")) {

            if (config == null) {
                throw new Exception("No config file found");
            }

            prop.load(config);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

//    get value of property
    public static String getProp(String key) {
        return prop.getProperty(key);
    }


}
