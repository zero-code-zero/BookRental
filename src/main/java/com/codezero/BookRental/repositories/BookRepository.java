package com.codezero.BookRental.repositories;

import com.codezero.BookRental.entitis.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitle(String title, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByTitleAndAuthor(String title, String author, Pageable pageable);
    List<Book> findByPublishedDate(LocalDate publishedDate);
    List<Book> findByAuthorAndPublishedDate(String author, LocalDate publishedDate);
}
