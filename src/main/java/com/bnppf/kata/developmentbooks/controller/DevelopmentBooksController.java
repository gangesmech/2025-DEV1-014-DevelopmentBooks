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
    private final com.bnppf.kata.developmentbooks.mapper.BookMapper bookMapper;

    public DevelopmentBooksController(PricingService pricingService,
            com.bnppf.kata.developmentbooks.mapper.BookMapper bookMapper) {
        this.pricingService = pricingService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/books")
    public List<com.bnppf.kata.developmentbooks.model.Book> getBooks() {
        return pricingService.getBooks().stream()
                .map(bookMapper::toModel)
                .toList();
    }

    @PostMapping("/price")
    public double calculatePrice(@RequestBody List<com.bnppf.kata.developmentbooks.model.Book> books) {
        var domainBooks = books.stream()
                .map(bookMapper::toDomain)
                .toList();
        return pricingService.calculatePrice(domainBooks);
    }
}
