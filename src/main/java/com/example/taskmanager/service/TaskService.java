package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
    }

    public Task create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        if (request.getOwnerId() != null) {
            User owner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getOwnerId()));
            task.setOwner(owner);
        }
        Task saved = taskRepository.save(task);
        log.info("Created task {}", saved.getId());
        return saved;
    }

    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }
        Task saved = taskRepository.save(task);
        log.info("Updated task {}", saved.getId());
        return saved;
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
        log.info("Deleted task {}", id);
    }
}
