package com.codezero.BookRental.service;

import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Rental;
import com.codezero.BookRental.entitis.Member;
import com.codezero.BookRental.exception.AlreadyExistsException;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import com.codezero.BookRental.repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class RentalServiceTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Test
    void 대여중인_도서는_대여할_수_없다() {

        RentalService service = new RentalService(bookRepository, memberRepository, rentalRepository);

        Book book = bookRepository.save(new Book("스트핑부트 초보강의", "코드제로", LocalDate.of(2025,4,19)));
        Member member = memberRepository.save(new Member("코드제로"));
        RentalRequest rentalRequest = new RentalRequest(member.getId(), book.getId());

        rentalRepository.save(new Rental(member, book));
        assertThatThrownBy(()->service.rentBook(rentalRequest))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("이미 대여 중입니다.");
    }
}