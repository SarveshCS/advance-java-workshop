package com.workshop.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.workshop.book.exception.BookNotFoundException;
import com.workshop.book.exception.DuplicateIsbnException;
import com.workshop.book.model.Book;
import com.workshop.book.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new DuplicateIsbnException("A book with ISBN " + book.getIsbn() + " already exists.");
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
