package com.codezero.BookRental.service;

import com.codezero.BookRental.dto.BookRequest;
import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Slf4j
@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookRequest bookRequest) {
        return bookRepository.save(new Book(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getPublishedDate()));
    }
    @Cacheable("books")
    public Page<Book> getBooks(String title, String author, Pageable pageable) {
        log.info("데이터베이스 조회, 캐시미스");
        if(title != null && author != null) {
            return bookRepository.findByTitleAndAuthor(title, author, pageable);
        } else if (title != null) {
            return bookRepository.findByTitle(title, pageable);
        } else if (author != null) {
            return bookRepository.findByAuthor(author, pageable);
        }
        return bookRepository.findAll(pageable);
    }
    @CachePut(value = "books", key="#id")
    public Book updateBook(Long id, Book updateBook) {
        Book book = bookRepository.save(updateBook);
        log.info("ID "+updateBook.getId() + "의 캐시를 업데이트했습니다.");
        return book;
    }
    @CacheEvict(value = "books", key="#id")
    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        log.info("ID "+id+"의 책 캐시가 삭제되었습니다.");
        return true;
    }
    @Cacheable(value="books", key="#id")
    public Book getBookById(Long id) {
        log.info("디비에서 책조회");
        return bookRepository.findById(id).orElseThrow(()->new NotFoundException("도서를 찾을 수 없습니다."));
    }
}
