package com.bnppf.kata.developmentbooks.controller;

import com.bnppf.kata.developmentbooks.domain.Book;
import com.bnppf.kata.developmentbooks.service.PricingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DevelopmentBooksController.class)
class DevelopmentBooksControllerTest {

    private static final String BOOKS_ENDPOINT = "/books";
    private static final String PRICE_ENDPOINT = "/price";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PricingService pricingService;

    @Test
    @DisplayName("GET /books should return all available books")
    void getBooksReturnsAllBooks() throws Exception {
        var books = List.of(Book.values());
        when(pricingService.getBooks()).thenReturn(books);

        mockMvc.perform(get(BOOKS_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    @DisplayName("POST /price should calculate and return correct price")
    void calculatePriceReturnsCorrectTotal() throws Exception {
        var books = List.of(Book.CLEAN_CODE, Book.THE_CLEAN_CODER);
        var expectedPrice = 95.0;
        when(pricingService.calculatePrice(books)).thenReturn(expectedPrice);

        mockMvc.perform(post(PRICE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(books)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPrice)));
    }
}
