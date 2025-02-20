package com.gefami.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gefami.model.Book;
import com.gefami.model.BookLoan;
import com.gefami.model.User;
import com.gefami.repository.BookLoanRepository;
import com.gefami.repository.BookRepository;
import com.gefami.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    
    public BookLoan borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        // Check if user already has an active loan
        if (bookLoanRepository.findByUserAndReturnDateIsNull(user).isPresent()) {
            throw new RuntimeException("User already has an active loan");
        }
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
                
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }
        
        BookLoan loan = BookLoan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(14))
                .build();
                
        return bookLoanRepository.save(loan);
    }
    
    public BookLoan returnBook(Long loanId) {
        BookLoan loan = bookLoanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
                
        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }
        
        loan.setReturnDate(LocalDateTime.now());
        return bookLoanRepository.save(loan);
    }
    
    public List<BookLoan> getOverdueLoans() {
        return bookLoanRepository.findByReturnDateIsNullAndDueDateBefore(LocalDateTime.now());
    }
    
    public List<BookLoan> getActiveLoans() {
        return bookLoanRepository.findByReturnDateIsNull();
    }
} 