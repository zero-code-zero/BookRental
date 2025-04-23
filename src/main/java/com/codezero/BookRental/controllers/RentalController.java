package com.codezero.BookRental.controllers;

import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.dto.RentalResponse;
import com.codezero.BookRental.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<RentalResponse> rental(@RequestBody RentalRequest request) {
        RentalResponse response = rentalService.rentBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{rentId}/renturn")
    public ResponseEntity<Void> returnBook(Long rentId) {
        rentalService.returnBook(rentId);
        return ResponseEntity.noContent().build();
    }
}
