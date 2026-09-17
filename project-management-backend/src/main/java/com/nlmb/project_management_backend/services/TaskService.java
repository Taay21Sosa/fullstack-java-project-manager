package com.nlmb.project_management_backend.services;

import com.nlmb.project_management_backend.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> getAllTasks(UUID projectId);
    Task createTask(UUID projectId, Task task);
    Optional<Task> getTask(UUID projectId, UUID taskId);
    Task updateTask(UUID projectId, UUID taskId, Task task);
    void deleteTask(UUID projectId, UUID taskId);
}
