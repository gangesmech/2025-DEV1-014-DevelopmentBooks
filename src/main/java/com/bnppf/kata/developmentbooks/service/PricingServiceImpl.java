package com.bnppf.kata.developmentbooks.service;

import com.bnppf.kata.developmentbooks.domain.Book;
import com.bnppf.kata.developmentbooks.domain.ShoppingBasket;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PricingServiceImpl implements PricingService {

    @Override
    public double calculatePrice(List<Book> books) {
        var shoppingBasket = new ShoppingBasket();
        Optional.ofNullable(books)
                .orElse(Collections.emptyList())
                .forEach(shoppingBasket::add);
        return shoppingBasket.calculate();
    }

    @Override
    public List<Book> getBooks() {
        return List.of(Book.values());
    }
}
