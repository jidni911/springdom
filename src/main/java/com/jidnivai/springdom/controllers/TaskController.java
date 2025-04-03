package com.jidnivai.springdom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.springdom.entity.Task;
import com.jidnivai.springdom.security.services.UserDetailsImpl;
import com.jidnivai.springdom.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @GetMapping
    public Page<Task> getAll(
        @RequestParam(required = false,defaultValue = "1") int page,
        @RequestParam(required = false,defaultValue = "10") int size,
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        try {
            return taskService.getAllTasks(page, size, userDetailsImpl.getUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    @PostMapping
    public Task create(
        @RequestBody Task entity,
        @AuthenticationPrincipal UserDetailsImpl user
    ) {
        return taskService.createTask(entity, user.getUser());
    }
    
    
}
