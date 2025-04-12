package com.jidnivai.springdom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.entity.Book;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Page<Book> getAllBooks(int page, int size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookRepository.findAll(pageable);
        // System.out.println(books);
        return books;
    }

    public Book createBook(Book entity, User user) {
        //Todo add validation
        return bookRepository.save(entity);

    }
}
