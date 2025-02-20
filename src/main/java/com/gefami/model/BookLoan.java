package com.gefami.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    @Column(name = "loan_date")
    private LocalDateTime loanDate;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    
    public boolean isOverdue() {
        return returnDate == null && LocalDateTime.now().isAfter(dueDate);
    }
} 