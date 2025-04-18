package com.codezero.BookRental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookRequest {
    @NotBlank(message = "{book.title.notblank}")
    private final String title;
    @NotBlank(message = "{book.author.notblank}")
    private final String author;
    @NotNull(message = "{book.publishedDate.notblank}")
    private final LocalDate publishedDate;

    public BookRequest(String title, String author, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

}
