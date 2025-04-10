package com.jidnivai.springdom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
}
