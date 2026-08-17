package com.example;

import java.util.HashMap;
import java.util.Map;

public class MutableKeyCatalog {
    private final Map<String, String> products = new HashMap<>();

    public String registerAndRead(String sku, String initialRegion, String changedRegion) {
        products.put(sku, "registered");
        // 地域は商品属性であり、Mapの安定した識別子ではない。
        return products.get(sku);
    }
}
