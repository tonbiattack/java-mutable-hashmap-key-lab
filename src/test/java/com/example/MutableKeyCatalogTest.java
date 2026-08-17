package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MutableKeyCatalogTest {
    @Test
    void キー属性を変更しても登録済み商品を読める() {
        String actual = new MutableKeyCatalog().registerAndRead("SKU-1", "JP", "US");
        System.out.println("[evidence] lookup=" + actual);
        assertEquals("registered", actual);
    }
}
