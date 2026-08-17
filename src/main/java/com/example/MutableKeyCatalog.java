package com.example;

import java.util.HashMap;
import java.util.Map;

public class MutableKeyCatalog {
    private final Map<ProductKey, String> products = new HashMap<>();

    public String registerAndRead(String sku, String initialRegion, String changedRegion) {
        ProductKey key = new ProductKey(sku, initialRegion);
        products.put(key, "registered");
        key.region = changedRegion;
        return products.get(key);
    }

    static final class ProductKey {
        private final String sku;
        private String region;

        ProductKey(String sku, String region) {
            this.sku = sku;
            this.region = region;
        }

        @Override public boolean equals(Object other) {
            return other instanceof ProductKey that && sku.equals(that.sku) && region.equals(that.region);
        }
        @Override public int hashCode() { return 31 * sku.hashCode() + region.hashCode(); }
    }
}
