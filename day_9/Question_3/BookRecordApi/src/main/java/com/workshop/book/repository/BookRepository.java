package com.workshop.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
