package com.bazaarstores.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        String browser = System.getProperty("browser");
        return browser != null ? browser : properties.getProperty("browser");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }

    public static String getCustomerEmail() {
        return properties.getProperty("customer.email");
    }

    public static String getAdminEmail() {
        return properties.getProperty("admin.email");
    }

    public static String getStoreManagerEmail() {
        return properties.getProperty("storemanager.email");
    }

    public static String getDefaultPassword() {
        return properties.getProperty("default.password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }

    public static boolean isHeadless() {
        String headless = System.getProperty("headless");
        return headless != null ? Boolean.parseBoolean(headless) :
                Boolean.parseBoolean(properties.getProperty("headless"));
    }
}