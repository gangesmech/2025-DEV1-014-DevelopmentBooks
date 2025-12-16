package com.bnppf.kata.developmentbooks;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {
    private final Map<Book, Integer> bookQuantities = new HashMap<>();
    private static final double BOOK_PRICE = 50.0;
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25);

    public void add(Book book) {
        bookQuantities.merge(book, 1, Integer::sum);
    }

    public double calculate() {
        var distinctBooksCount = bookQuantities.size();
        var discount = DISCOUNTS.getOrDefault(distinctBooksCount, 0.0);
        var discountedPrice = distinctBooksCount * BOOK_PRICE * (1.0 - discount);
        var totalBooksCount = bookQuantities.values().stream().mapToInt(Integer::intValue).sum();
        var remainingBooksCount = totalBooksCount - distinctBooksCount;
        return discountedPrice + (remainingBooksCount * BOOK_PRICE);
    }
}
