package com.codezero.BookRental.dto;

import jakarta.validation.constraints.NotBlank;

public class RentalRequest {
    @NotBlank(message = "{rental.memberId.notblank}")
    private final Long memberId;
    @NotBlank(message = "{rental.bookId.notblank}")
    private final Long bookId;

    public RentalRequest(Long memberId, Long bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getBookId() {
        return bookId;
    }
}
