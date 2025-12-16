package com.bnppf.kata.developmentbooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingBasket {
    private final List<Book> books = new ArrayList<>();
    private static final Map<Integer, Double> DISCOUNTS = Map.of(2, 0.05);

    public void add(Book book) {
        books.add(book);
    }

    public double calculate() {
        var distinctBooksCount = (int) books.stream().map(Book::title).distinct().count();
        var discount = DISCOUNTS.getOrDefault(distinctBooksCount, 0.0);
        return books.size() * 50.0 * (1.0 - discount);
    }
}
