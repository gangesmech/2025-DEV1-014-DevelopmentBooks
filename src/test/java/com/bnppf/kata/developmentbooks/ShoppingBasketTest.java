package com.bnppf.kata.developmentbooks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingBasketTest {

    private static final double BOOK_PRICE = 50.0;

    @Test
    void emptyBasketDescription() {
        var basket = new ShoppingBasket();
        assertEquals(0.0, basket.calculate());
    }

    @Test
    void singleBookCostsFifty() {
        var basket = new ShoppingBasket();
        basket.add(new Book("Clean Code"));
        assertEquals(BOOK_PRICE, basket.calculate());
    }

    record Book(String title) {
    }
}
