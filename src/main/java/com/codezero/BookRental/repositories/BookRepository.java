package com.codezero.BookRental.repositories;

import com.codezero.BookRental.entitis.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublishedDate(LocalDate publishedDate);
    List<Book> findByAuthorAndPublishedDate(String author, LocalDate publishedDate);
}
