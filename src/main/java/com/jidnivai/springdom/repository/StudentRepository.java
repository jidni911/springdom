package com.jidnivai.springdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
