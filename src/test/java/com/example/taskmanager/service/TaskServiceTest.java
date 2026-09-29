package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        taskService = new TaskService(taskRepository, userRepository);
    }

    @Test
    void createSavesTaskWithGivenTitle() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Write tests");

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task created = taskService.create(request);

        assertEquals("Write tests", created.getTitle());
    }

    @Test
    void findByIdThrowsWhenMissing() {
        when(taskRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.findById(42L));
    }
}
