package com.codezero.BookRental;

import com.codezero.BookRental.entitis.Book;
import com.codezero.BookRental.entitis.Member;
import com.codezero.BookRental.repositories.BookRepository;
import com.codezero.BookRental.repositories.MemberRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DummyDataGenerator implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final Faker faker = new Faker(Locale.KOREA);

    public DummyDataGenerator(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 책
        List<Book> books = new ArrayList<>();
        for (int i=0;i<1000;i++) {
            Book book = new Book(faker.book().title(),faker.book().author(),faker.timeAndDate().birthday());
            books.add(book);
        }
        bookRepository.saveAll(books);

        // 멤버
        List<Member> members = new ArrayList<>();
        for (int i=0;i<100;i++) {
            Member member = new Member(faker.name().name());
            members.add(member);
        }
        memberRepository.saveAll(members);
    }
}
