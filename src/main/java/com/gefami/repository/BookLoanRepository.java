package com.gefami.repository;

import com.gefami.model.BookLoan;
import com.gefami.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    Optional<BookLoan> findByUserAndReturnDateIsNull(User user);
    List<BookLoan> findByReturnDateIsNull();
    List<BookLoan> findByReturnDateIsNullAndDueDateBefore(LocalDateTime date);
} 