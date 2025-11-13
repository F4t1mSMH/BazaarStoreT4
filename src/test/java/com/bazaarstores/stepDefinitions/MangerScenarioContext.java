package com.bazaarstores.stepDefinitions;

import java.util.HashMap;
import java.util.Map;

public class MangerScenarioContext {
    public static final String DUPLICATE_SKU = "3945165";
    public String originalProductName;
    public String editedProductName;
    public String productToVerifyName;
    public Map<String, Object> productPayload = new HashMap<>();
    public int productId;
}
