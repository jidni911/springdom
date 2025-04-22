package com.jidnivai.springdom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.entity.Book;
import com.jidnivai.springdom.entity.BookRequests;
import com.jidnivai.springdom.entity.Student;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.enums.RequestStatus;
import com.jidnivai.springdom.repository.BookRepository;
import com.jidnivai.springdom.repository.BookRequestRepository;
import com.jidnivai.springdom.repository.StudentRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookRequestRepository bookRequestRepository;

    @Autowired
    StudentRepository studentRepository;

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

    public Boolean borrowBook(Long bookId, User user) {
        Book book = bookRepository.findById(bookId).get();
        Student student = studentRepository.findById(user.getId()).get();
        if (bookRequestRepository.existsByBookAndUser(book, user)) {
            
            return false;
        }
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
            BookRequests bookRequest = new BookRequests();
            bookRequest.setStudent(student);
            bookRequest.setBook(book);
            bookRequest.setStatus(RequestStatus.REQUESTED);
            bookRequestRepository.save(bookRequest);
            return true;
        } 
        return false;
    }

    public List<Book> getBorrowList(User user) {
        return bookRequestRepository.findByUserAndStatusNot(user, RequestStatus.RETURNED).stream().map(br -> br.getBook()).toList();
    }

    public List<BookRequests> getBookRequests(User user) {
        return bookRequestRepository.findByUserAndStatusIn(user, List.of(RequestStatus.REQUESTED, RequestStatus.DECLINED));
    }
}
