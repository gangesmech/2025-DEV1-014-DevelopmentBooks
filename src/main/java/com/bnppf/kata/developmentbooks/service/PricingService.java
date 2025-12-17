package com.bnppf.kata.developmentbooks.service;

import com.bnppf.kata.developmentbooks.domain.Book;

import java.util.List;

public interface PricingService {
    double calculatePrice(List<Book> books);

    List<Book> getBooks();
}
