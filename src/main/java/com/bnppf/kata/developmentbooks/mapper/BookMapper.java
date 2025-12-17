package com.bnppf.kata.developmentbooks.mapper;

import com.bnppf.kata.developmentbooks.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    com.bnppf.kata.developmentbooks.domain.Book toDomain(Book bookModel);

    Book toModel(com.bnppf.kata.developmentbooks.domain.Book book);
}
