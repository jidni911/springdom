package com.jidnivai.springdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Book;
import com.jidnivai.springdom.entity.BookRequests;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.enums.RequestStatus;

public interface BookRequestRepository extends JpaRepository<BookRequests, Long> {


    boolean existsByBookAndUser(Book book, User user);

    List<BookRequests> findByUserAndStatusNot(User user, RequestStatus status);

    List<BookRequests> findByUserAndStatusIn(User user, List<RequestStatus> of);
    
}
