package com.bnppf.kata.developmentbooks;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public double calculate() {
        if (books.size() == 2 && books.stream().map(Book::title).distinct().count() == 2) {
            return books.size() * 50.0 * 0.95;
        }
        return books.size() * 50.0;
    }
}
