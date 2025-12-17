package com.bnppf.kata.developmentbooks.domain;

public record Book(String title, String author, int year) {

    public static final Book CLEAN_CODE = new Book("Clean Code", "Robert C. Martin", 2008);
    public static final Book THE_CLEAN_CODER = new Book("The Clean Coder", "Robert C. Martin", 2011);
    public static final Book CLEAN_ARCHITECTURE = new Book("Clean Architecture", "Robert C. Martin", 2017);
    public static final Book TEST_DRIVEN_DEVELOPMENT = new Book("Test Driven Development", "Kent Beck", 2002);
    public static final Book LEGACY_CODE = new Book("Working Effectively with Legacy Code", "Michael Feathers", 2004);

    public static Book[] values() {
        return new Book[] { CLEAN_CODE, THE_CLEAN_CODER, CLEAN_ARCHITECTURE, TEST_DRIVEN_DEVELOPMENT, LEGACY_CODE };
    }
}
