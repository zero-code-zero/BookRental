package com.codezero.BookRental.dto;

import lombok.Getter;

public record LoginRequest(@Getter String username, @Getter String password) {

}
