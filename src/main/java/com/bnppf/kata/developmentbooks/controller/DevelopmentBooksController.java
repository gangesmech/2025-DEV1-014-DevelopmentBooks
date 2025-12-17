package com.bnppf.kata.developmentbooks.controller;

import com.bnppf.kata.developmentbooks.domain.Book;
import com.bnppf.kata.developmentbooks.service.PricingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DevelopmentBooksController {

    private final PricingService pricingService;

    public DevelopmentBooksController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return pricingService.getBooks();
    }

    @PostMapping("/price")
    public double calculatePrice(@RequestBody List<Book> books) {
        return pricingService.calculatePrice(books);
    }
}
