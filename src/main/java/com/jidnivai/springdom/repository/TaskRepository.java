package com.jidnivai.springdom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Task;
import com.jidnivai.springdom.entity.User;

public interface TaskRepository extends JpaRepository<Task,Long> {


    Page<Task> findAllByUser(User user, Pageable pageable);
    
}
