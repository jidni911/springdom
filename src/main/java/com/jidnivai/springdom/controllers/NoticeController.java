package com.jidnivai.springdom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.springdom.entity.Notice;
import com.jidnivai.springdom.security.services.UserDetailsImpl;
import com.jidnivai.springdom.service.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping
    Page<Notice> getNotices(
        @RequestParam(required = false,defaultValue = "0") int page,
        @RequestParam(required = false,defaultValue = "10") int size,
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        try {
            return noticeService.getNotices( page, size, userDetailsImpl.getUser() );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping
    public Notice create(
        @RequestBody Notice entity,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        try {
            return noticeService.createNotice(entity, user.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
