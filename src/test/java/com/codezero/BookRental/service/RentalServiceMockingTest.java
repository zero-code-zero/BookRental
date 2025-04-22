package com.codezero.BookRental.service;

import com.codezero.BookRental.dto.RentalRequest;
import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Member;
import com.codezero.BookRental.entitis.Rental;
import com.codezero.BookRental.exception.AlreadyExistsException;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import com.codezero.BookRental.repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceMockingTest {
    @InjectMocks
    private RentalService rentalService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private RentalRepository rentalRepository;

    @Test
    void 책을_정상적으로_대여하는경우() {
        //given
        Book book = new Book("스프링부트 초보강의1", "제로코드", LocalDate.of(2000,1,1));
        Member member = new Member("제로코드");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(rentalRepository.existsByBookAndReturnedDateIsNull(book)).thenReturn(false);
//        when(rentalRepository.findByIdAndReturnedDateIsNull(1L)).thenReturn(Optional.empty());

        //when
        rentalService.rentBook(new RentalRequest(1L, 1L));

        //then
        verify(rentalRepository).save(any(Rental.class));
    }

    @Test
    void 이미_대여된_책을_다시_대여_요청하는_경우(){
        //given
        Book book = new Book("스프링부트 초보강의1", "제로코드", LocalDate.of(2000,1,1));
        Member member = new Member("제로코드");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(rentalRepository.existsByBookAndReturnedDateIsNull(book)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> rentalService.rentBook(new RentalRequest(1L, 1L)));
    }

    @Test
    void 정상반납() {
        Book book = new Book("스프링부트 초보강의1", "제로코드", LocalDate.of(2000,1,1));
        Member member = new Member("제로코드");
        Rental rental = new Rental(member, book);

        when(rentalRepository.findByIdAndReturnedDateIsNull(1L)).thenReturn(Optional.of(rental));

        rentalService.returnBook(1L);

        assertNotNull(rental.getReturnedDate());
    }
}