package com.codezero.BookRental.repositories;

import com.codezero.BookRental.entitis.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
