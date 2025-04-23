package com.codezero.BookRental.dto;

import java.time.LocalDate;

public record RentalResponse(Long rentalId, String bookTitle, String memberName, LocalDate rentedDate) {

}
