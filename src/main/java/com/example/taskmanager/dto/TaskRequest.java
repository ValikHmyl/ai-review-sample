package com.example.taskmanager.dto;

import com.example.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {

    @NotBlank
    @Size(max = 120)
    private String title;

    @Size(max = 2000)
    private String description;

    private TaskStatus status;

    private Long ownerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
