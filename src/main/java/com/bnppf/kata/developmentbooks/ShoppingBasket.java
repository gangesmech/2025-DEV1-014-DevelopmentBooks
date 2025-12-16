package com.bnppf.kata.developmentbooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
        if (bookQuantities.isEmpty()) {
            return 0.0;
        }
        return calculateLowestPricePossible(new ArrayList<>(bookQuantities.values()));
    }

    private double calculateLowestPricePossible(List<Integer> countsOfEachBook) {
        countsOfEachBook.removeIf(count -> count == 0);

        if (countsOfEachBook.isEmpty()) {
            return 0.0;
        }

        countsOfEachBook.sort(Collections.reverseOrder());

        int numberOfDifferentBooksAvailable = countsOfEachBook.size();
        return IntStream.rangeClosed(1, numberOfDifferentBooksAvailable)
                .mapToDouble(sizeOfDiscountedSet -> calculatePriceForSet(sizeOfDiscountedSet, countsOfEachBook))
                .min()
                .orElse(0.0);
    }

    private double calculatePriceForSet(int sizeOfDiscountedSet, List<Integer> countsOfEachBook) {
        ArrayList<Integer> remainingBookCountsAfterSetRemoval = new ArrayList<>(countsOfEachBook);

        IntStream.range(0, sizeOfDiscountedSet)
                .forEach(index -> remainingBookCountsAfterSetRemoval.set(index,
                        remainingBookCountsAfterSetRemoval.get(index) - 1));

        double discountPercentage = DISCOUNTS.getOrDefault(sizeOfDiscountedSet, 0.0);
        double priceForCurrentSet = sizeOfDiscountedSet * BOOK_PRICE * (1.0 - discountPercentage);
        return priceForCurrentSet + calculateLowestPricePossible(remainingBookCountsAfterSetRemoval);
    }
}
