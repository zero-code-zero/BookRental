package com.codezero.BookRental.repositories;

import com.codezero.BookRental.entitis.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
