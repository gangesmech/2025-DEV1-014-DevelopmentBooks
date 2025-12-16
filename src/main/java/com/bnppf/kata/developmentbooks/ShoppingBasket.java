package com.bnppf.kata.developmentbooks;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public double calculate() {
        return books.size() * 50.0;
    }
}
