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
import com.bnppf.kata.developmentbooks.mapper.BookMapper;
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

    @MockBean
    private BookMapper bookMapper;

    @Test
    @DisplayName("GET /books should return all available books")
    void getBooksReturnsAllBooks() throws Exception {
        var domainBooks = List.of(Book.values());
        var dtoBooks = createAndMockBookModels(domainBooks);

        when(pricingService.getBooks()).thenReturn(domainBooks);

        mockMvc.perform(get(BOOKS_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoBooks)));
    }

    @Test
    @DisplayName("POST /price should calculate and return correct price")
    void calculatePriceReturnsCorrectTotal() throws Exception {
        var domainBooks = List.of(Book.CLEAN_CODE, Book.THE_CLEAN_CODER);
        var dtoBooks = createAndMockBookModels(domainBooks);
        var expectedPrice = 95.0;

        when(pricingService.calculatePrice(domainBooks)).thenReturn(expectedPrice);

        mockMvc.perform(post(PRICE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoBooks)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPrice)));
    }

    private List<com.bnppf.kata.developmentbooks.model.Book> createAndMockBookModels(List<Book> domainBooks) {
        var dtoBooks = createBookModels(domainBooks);
        mockBookMapper(domainBooks, dtoBooks);
        return dtoBooks;
    }

    private List<com.bnppf.kata.developmentbooks.model.Book> createBookModels(List<Book> domainBooks) {
        return domainBooks.stream()
                .map(book -> new com.bnppf.kata.developmentbooks.model.Book()
                        .title(book.title())
                        .author(book.author())
                        .year(book.year()))
                .toList();
    }

    private void mockBookMapper(List<Book> domainBooks, List<com.bnppf.kata.developmentbooks.model.Book> dtoBooks) {
        for (int index = 0; index < domainBooks.size(); index++) {
            when(bookMapper.toModel(domainBooks.get(index))).thenReturn(dtoBooks.get(index));
            when(bookMapper.toDomain(org.mockito.ArgumentMatchers.refEq(dtoBooks.get(index))))
                    .thenReturn(domainBooks.get(index));
        }
    }
}
