package com.bnppf.kata.developmentbooks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class ShoppingBasketTest {

    private static final double BOOK_PRICE = 50.0;
    private static final String[] BOOKS = {
            "Clean Code",
            "The Clean Coder",
            "Clean Architecture",
            "Test Driven Development by Example",
            "Working Effectively with Legacy Code"
    };

    @Test
    void emptyBasketDescription() {
        var basket = new ShoppingBasket();
        assertEquals(0.0, basket.calculate());
    }

    @Test
    void singleBookCostsFifty() {
        var basket = new ShoppingBasket();
        basket.add(new Book(BOOKS[0]));
        assertEquals(BOOK_PRICE, basket.calculate());
    }

    @Test
    void twoDifferentBooksDiscount() {
        var basket = new ShoppingBasket();
        basket.add(new Book(BOOKS[0]));
        basket.add(new Book(BOOKS[1]));
        assertEquals(95.0, basket.calculate());
    }

    @ParameterizedTest
    @MethodSource("provideBookCountsAndExpectedPrices")
    void distinctBooksReceiveDiscounts(int distinctBooksCount, double expectedPrice) {
        var basket = new ShoppingBasket();
        IntStream.range(0, distinctBooksCount)
                .mapToObj(i -> new Book(BOOKS[i]))
                .forEach(basket::add);
        assertEquals(expectedPrice, basket.calculate());
    }

    static Stream<Arguments> provideBookCountsAndExpectedPrices() {
        return Stream.of(
                of(3, 135.0),
                of(4, 160.0),
                of(5, 187.5));
    }
}
