package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOwnerId(Long ownerId);

    @Query(value = "SELECT * FROM tasks t WHERE t.title = ?1", nativeQuery = true)
    List<Task> findByTitleRaw(String title);
}
