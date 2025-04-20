package com.codezero.BookRental.service;

import com.codezero.BookRental.BookRequest;
import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public List<Book> getBooks() {
        return books;
    }

    public Book updateBook(Long id, Book updateBook) {
        for (Book book : books) {
            if(book.getId().equals(id)) {
                book.setTitle(updateBook.getTitle());
                return book;
            }
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        for(Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
            Book book = iterator.next();
            if(book.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new NotFoundException("도서를 찾을 수 없습니다."));
    }
}
