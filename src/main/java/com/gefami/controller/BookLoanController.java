package com.gefami.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gefami.model.BookLoan;
import com.gefami.service.BookLoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;
    
    @PostMapping("/borrow")
    public ResponseEntity<BookLoan> borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {
        return ResponseEntity.ok(bookLoanService.borrowBook(userId, bookId));
    }
    
    @PostMapping("/{loanId}/return")
    public ResponseEntity<BookLoan> returnBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(bookLoanService.returnBook(loanId));
    }
    
    @GetMapping("/overdue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookLoan>> getOverdueLoans() {
        return ResponseEntity.ok(bookLoanService.getOverdueLoans());
    }
    
    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookLoan>> getActiveLoans() {
        return ResponseEntity.ok(bookLoanService.getActiveLoans());
    }
} 