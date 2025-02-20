package com.gefami.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gefami.model.Book;
import com.gefami.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    
    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
} 