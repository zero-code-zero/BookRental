package com.codezero.BookRental;

import com.codezero.BookRental.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;


public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        BookRepository bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void 책을_생성한다() {
        BookRequest bookRequest = new BookRequest("테스트책", "홍길동", LocalDate.of(2000,4,10));

        Book saved = bookService.createBook(bookRequest);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("테스트책");
        assertThat(bookService.getBooks()).hasSize(1);
    }

    @Test
    void 책을_수정한다() {
        BookRequest bookRequest = new BookRequest("원본책","홍길동",LocalDate.of(2000,4,10));


        bookService.createBook(bookRequest);

        Book update = new Book();
        String updateBookName = "수정된 책";
        update.setTitle(updateBookName);

        Book updateBook = bookService.updateBook(1L, update);

        assertThat(updateBook.getTitle()).isEqualTo(updateBookName);
    }

    @Test
    void 책을_삭제한다() {
        BookRequest bookRequest = new BookRequest("스프링부트 초보강의", "홍길동",LocalDate.of(2000,4,10));

        bookService.createBook(bookRequest);

        boolean deleted = bookService.deleteBook(1L);

        assertThat(deleted).isTrue();
        assertThat(bookService.getBooks()).isEmpty();
    }

    Book createBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return book;
    }

    @Test
    void 책을_조회한다() {
        assertThatThrownBy(()->bookService.getBookById(11L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("도서를 찾을 수 없습니다.");
    }
}
