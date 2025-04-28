package com.codezero.BookRental;

import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

@SpringBootApplication
@EnableCaching
public class BookRentalApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookRentalApplication.class, args);

	}
	@Bean
	CommandLineRunner runner(BookRepository bookRepository) {
		return args -> {
			bookRepository.save(new Book("Spring Boot 시작하기", "홍길동", LocalDate.of(2025, 3, 2)));
			bookRepository.save(new Book("스프링부트 완전정복", "코드제로", LocalDate.of(2025, 4,7)));
			bookRepository.save(new Book("스프링 고급", "홍길동", LocalDate.of(2025,3,1)));
			System.out.println("[전체조회]");
			bookRepository.findAll().forEach(book -> System.out.println(book.getTitle()));
			System.out.println("[작가로 조회]");
			bookRepository.findByAuthor("홍길동", PageRequest.of(0,10)).forEach(book -> {
				System.out.println(book.getTitle() + "/" + book.getAuthor() + "/" + book.getPublishedDate());
			});
			System.out.println("[작가와 출판일로 조회]");
			bookRepository.findByAuthorAndPublishedDate("홍길동", LocalDate.of(2025, 3, 1)).forEach(
					book-> {
						System.out.println(book.getTitle() + "/" + book.getAuthor() + "/" + book.getPublishedDate());
					}
			);
		};
	}

}
