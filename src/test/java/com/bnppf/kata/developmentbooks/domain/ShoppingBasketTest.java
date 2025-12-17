package com.bnppf.kata.developmentbooks.domain;

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

    @Test
    void emptyBasketDescription() {
        var basket = new ShoppingBasket();
        assertEquals(0.0, basket.calculate());
    }

    @Test
    void singleBookCostsFifty() {
        var basket = new ShoppingBasket();
        basket.add(Book.CLEAN_CODE);
        assertEquals(BOOK_PRICE, basket.calculate());
    }

    @Test
    void twoDifferentBooksDiscount() {
        var basket = new ShoppingBasket();
        basket.add(Book.CLEAN_CODE);
        basket.add(Book.THE_CLEAN_CODER);
        assertEquals(95.0, basket.calculate());
    }

    @Test
    void twoDuplicateAndOneUniqueBook() {
        var basket = new ShoppingBasket();
        basket.add(Book.CLEAN_CODE);
        basket.add(Book.THE_CLEAN_CODER);
        basket.add(Book.CLEAN_CODE);
        assertEquals(145.0, basket.calculate());
    }

    @Test
    void optimalDiscountForMultipleSets() {
        var basket = new ShoppingBasket();
        basket.add(Book.CLEAN_CODE);
        basket.add(Book.CLEAN_CODE);
        basket.add(Book.THE_CLEAN_CODER);
        basket.add(Book.THE_CLEAN_CODER);
        basket.add(Book.CLEAN_ARCHITECTURE);
        basket.add(Book.CLEAN_ARCHITECTURE);
        basket.add(Book.TEST_DRIVEN_DEVELOPMENT);
        basket.add(Book.LEGACY_CODE);
        assertEquals(320.0, basket.calculate());
    }

    @ParameterizedTest
    @MethodSource("provideBookCountsAndExpectedPrices")
    void distinctBooksReceiveDiscounts(int distinctBooksCount, double expectedPrice) {
        var basket = new ShoppingBasket();
        IntStream.range(0, distinctBooksCount)
                .mapToObj(i -> Book.values()[i])
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
