package com.bnppf.kata.developmentbooks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingBasketTest {

    @Test
    void emptyBasketDescription() {
        var basket = new ShoppingBasket();
        assertEquals(0.0, basket.calculate());
    }
}
