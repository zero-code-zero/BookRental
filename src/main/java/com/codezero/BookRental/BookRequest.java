package com.codezero.BookRental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookRequest {
    @NotBlank(message = "{book.title.notblank}")
    private String title;
    @NotBlank(message = "{book.author.notblank}")
    private String author;
    @NotNull(message = "{book.publishedDate.notblank}")
    private LocalDate publishedDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
