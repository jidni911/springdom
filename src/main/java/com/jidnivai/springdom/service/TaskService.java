package com.jidnivai.springdom.service;

import com.jidnivai.springdom.entity.Task;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getAllTasks(int page, int size, User user) {
    Pageable pageable = PageRequest.of(page, size);
    return taskRepository.findAllByUser(user, pageable);
}

    public Task createTask(Task entity, User user) {
        entity.setUser(user);
        return taskRepository.save(entity);
        
    }

}