package com.bnppf.kata.developmentbooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingBasket {
    private final List<Book> books = new ArrayList<>();
    private static final double BOOK_PRICE = 50.0;
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25);

    public void add(Book book) {
        books.add(book);
    }

    public double calculate() {
        var distinctBooksCount = (int) books.stream().map(Book::title).distinct().count();
        var discount = DISCOUNTS.getOrDefault(distinctBooksCount, 0.0);
        var discountedPrice = distinctBooksCount * BOOK_PRICE * (1.0 - discount);
        var remainingBooksCount = books.size() - distinctBooksCount;
        return discountedPrice + (remainingBooksCount * BOOK_PRICE);
    }
}
