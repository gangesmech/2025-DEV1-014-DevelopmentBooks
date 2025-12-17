package com.bnppf.kata.developmentbooks.service;

import com.bnppf.kata.developmentbooks.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PricingServiceTest {

    private final PricingService pricingService = new PricingServiceImpl();

    @Test
    @DisplayName("Calculate price should delegate to domain logic")
    void calculatePriceDelegatesToDomain() {
        var books = List.of(Book.CLEAN_CODE, Book.THE_CLEAN_CODER);
        var price = pricingService.calculatePrice(books);
        Assertions.assertEquals(95.0, price);
    }

    @Test
    @DisplayName("Get available books should return all books")
    void getAvailableBooksReturnsAllBooks() {
        var books = pricingService.getBooks();
        Assertions.assertTrue(books.containsAll(List.of(Book.values())));
        Assertions.assertEquals(Book.values().length, books.size());
    }
}
