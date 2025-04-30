package com.codezero.BookRental.service;

import com.codezero.BookRental.dto.RentalDto;
import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.dto.RentalResponse;
import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Rental;
import com.codezero.BookRental.entitis.Member;
import com.codezero.BookRental.exception.AlreadyExistsException;
import com.codezero.BookRental.exception.NotFoundException;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import com.codezero.BookRental.repositories.RentalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
    @Transactional
    public RentalResponse rentBook(RentalRequest request) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(()->new NotFoundException("해당하는 책이 없습니다."));
        Member member = userRepository.findById(request.memberId()).orElseThrow(()->new NotFoundException("사용자가 없습니다."));

        boolean isRented = rentalRepository.existsByBookAndReturnedDateIsNull(book);
        if (isRented) throw new AlreadyExistsException("이미 대여 중입니다.");


        Rental rental = rentalRepository.save(new Rental(member, book));
        return new RentalResponse(rental.getId(), book.getTitle(), member.getName(), rental.getRentedDate());
    }
    @Transactional
    public void returnBook(Long rentalId) {
        Rental rental = rentalRepository.findByIdAndReturnedDateIsNull(rentalId)
                .orElseThrow(()->new NotFoundException("대여중인 책이 없습니다."));
        rental.setReturnedDate(LocalDate.now());
    }

    public Page<RentalDto> getRentals(Pageable pageable) {
        return rentalRepository.findAll(pageable).map(r->new RentalDto(
                r.getId(),
                r.getBook().getTitle(),
                r.getMember().getName(),
                r.getRentedDate(),
                r.getReturnedDate()
        ));
    }
}
