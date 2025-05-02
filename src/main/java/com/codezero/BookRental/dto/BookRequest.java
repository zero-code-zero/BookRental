package com.codezero.BookRental.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookRequest {
    @NotNull(message = "{book.title.notblank}")
    @Schema(description = "책 제목", example = "자바의 정석")
    private final String title;
    @NotNull(message = "{book.author.notblank}")
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
