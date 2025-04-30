package com.codezero.BookRental.repositories;

import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    boolean existsByBookAndReturnedDateIsNull(Book book);
    Optional<Rental> findByIdAndReturnedDateIsNull(Long bookId);
    @EntityGraph(attributePaths = {"book", "member"})
    Page<Rental> findAll(Pageable pageable);
}
