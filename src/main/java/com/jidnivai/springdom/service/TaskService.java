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

    public void deleteTask(Long taskId, User user) {
        Task task = taskRepository.findById(taskId).get();
        if (task.getUser().getId().equals(user.getId())) {
            taskRepository.delete(task);
        }
    }

    public Task getTaskById(Long taskId, User user) {
        Task task = taskRepository.findById(taskId).get();
        if (task.getUser().getId().equals(user.getId())) {
            return task;
        }
        return null;
    }

    public Task updateTask(Long taskId, Task task, User user) {
        Task task1 = taskRepository.findById(taskId).get();
        if (task1.getUser().getId().equals(user.getId())) {
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            task1.setDone(task.isDone());
            task1.setDateTime(task.getDateTime());
            task1.setTaskType(task.getTaskType());
            return taskRepository.save(task1);
        }
        return null;
    }

    

}