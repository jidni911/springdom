package com.jidnivai.springdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
}
