package com.codezero.BookRental.controllers;

import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.service.RentalService;
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
    public ResponseEntity<Void> rental(@RequestBody RentalRequest request) {
        rentalService.rentBook(request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{rentId}/renturn")
    public ResponseEntity<Void> returnBook(Long rentId) {
        rentalService.returnBook(rentId);
        return ResponseEntity.noContent().build();
    }
}
