package com.jidnivai.springdom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jidnivai.springdom.repository.NoticeRepository;

@Service
public class NoticeService {
    
    @Autowired
    NoticeRepository noticeRepository;
}
