package com.codezero.BookRental.dto;

import jakarta.validation.constraints.NotBlank;

public record RentalRequest(@NotBlank(message = "{rental.memberId.notblank}") Long memberId,
                            @NotBlank(message = "{rental.bookId.notblank}") Long bookId) {

}
