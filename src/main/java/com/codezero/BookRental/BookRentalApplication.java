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
}
