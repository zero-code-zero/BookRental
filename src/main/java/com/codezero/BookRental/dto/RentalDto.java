package com.codezero.BookRental.dto;

import java.time.LocalDate;

public record RentalDto(Long id, String title, String memberName, LocalDate rentedDate, LocalDate returnedDate) {
}
