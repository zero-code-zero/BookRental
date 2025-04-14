package com.codezero.BookRental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
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
		};
	}

}
