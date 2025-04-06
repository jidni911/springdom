package com.jidnivai.springdom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.entity.Notice;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.repository.NoticeRepository;

@Service
public class NoticeService {
    
    @Autowired
    NoticeRepository noticeRepository;

    public Page<Notice> getNotices(int page, int size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        return noticeRepository.findAll(pageable);
    }

}
