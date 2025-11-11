package com.bazaarstores.stepDefinitions;

import java.util.HashMap;
import java.util.Map;

public class MangerScenarioContext {
    public static final String DUPLICATE_SKU = "999TEST";
    public String originalProductName;
    public static String editedProductName;
    public static String productToVerifyName;
    public static Map<String, Object> productPayload = new HashMap<>();
    public static int productId;
}
