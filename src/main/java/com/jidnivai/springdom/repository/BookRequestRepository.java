package com.jidnivai.springdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Book;
import com.jidnivai.springdom.entity.BookRequests;
import com.jidnivai.springdom.entity.Student;
import com.jidnivai.springdom.enums.RequestStatus;

public interface BookRequestRepository extends JpaRepository<BookRequests, Long> {


    boolean existsByBookAndStudent(Book book, Student student);

    List<BookRequests> findByStudentAndStatusNot(Student student, RequestStatus status);

    List<BookRequests> findByStudentAndStatusIn(Student student, List<RequestStatus> of);
    
}
