package com.codezero.BookRental.service;

import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Rental;
import com.codezero.BookRental.entitis.Member;
import com.codezero.BookRental.exception.AlreadyExistsException;
import com.codezero.BookRental.exception.NotFoundException;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import com.codezero.BookRental.repositories.RentalRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    private final BookRepository bookRepository;
    private final MemberRepository userRepository;
    private final RentalRepository rentalRepository;

    public RentalService(BookRepository bookRepository, MemberRepository userRepository, RentalRepository rentalRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public void rentBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new NotFoundException("해당하는 책이 없습니다."));
        Member member = userRepository.findById(userId).orElseThrow(()->new NotFoundException("사용자가 없습니다."));

        boolean isRented = rentalRepository.existsByBookAndReturnedDateIsNull(book);
        if (isRented) throw new AlreadyExistsException("이미 대여 중입니다.");

        Rental rental = new Rental(member, book);
        rentalRepository.save(rental);
    }
}
