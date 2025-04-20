package com.jidnivai.springdom.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.springdom.entity.Book;
import com.jidnivai.springdom.entity.BookRequests;
import com.jidnivai.springdom.security.services.UserDetailsImpl;
import com.jidnivai.springdom.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public Page<Book> getAllBooks(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "20") int size,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try {
            return bookService.getAllBooks(page, size, user.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Book createBook(@RequestBody Book entity, @AuthenticationPrincipal UserDetailsImpl user) {
      
        try {
            return bookService.createBook(entity, user.getUser());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    @GetMapping("/borrow/{bookId}")
    public Boolean borrow(@PathVariable Long bookId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return bookService.borrowBook(bookId, user.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    @GetMapping("/borrowList")
    public List<Book> getBorrowList(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return bookService.getBorrowList(user.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/bookRequests")
    public List<BookRequests> getBookRequests(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return bookService.getBookRequests(user.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    
    
    
}
