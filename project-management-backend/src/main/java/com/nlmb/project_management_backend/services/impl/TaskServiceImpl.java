package com.nlmb.project_management_backend.services.impl;

import com.nlmb.project_management_backend.domain.entities.Priority;
import com.nlmb.project_management_backend.domain.entities.Project;
import com.nlmb.project_management_backend.domain.entities.Status;
import com.nlmb.project_management_backend.domain.entities.Task;
import com.nlmb.project_management_backend.repositories.ProjectRepository;
import com.nlmb.project_management_backend.repositories.TaskRepository;
import com.nlmb.project_management_backend.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository,  ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Task> getAllTasks(UUID projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @Transactional
    @Override
    public Task createTask(UUID projectId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task already has an ID!");
        }

        if (task.getName() == null ||  task.getName().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty!");
        }

        Priority priority = Optional.ofNullable(task.getPriority())
                .orElse(Priority.LOW);

        Status status = Status.TO_DO;

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid project ID provided, or project does not exist!"
                ));

        LocalDateTime now = LocalDateTime.now();

        Task newTask = new Task(
                null,
                task.getName(),
                task.getDescription(),
                task.getDueDate(),
                status,
                priority,
                project,
                now,
                now
        );

        return taskRepository.save(newTask);
    }

    @Override
    public Optional<Task> getTask(UUID projectId, UUID taskId) {
        return taskRepository.findByProjectIdAndId(projectId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID projectId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Project ID cannot be empty!");
        }

        if (!Objects.equals(task.getId(), taskId)) {
            throw new IllegalArgumentException("Task ID does not match!");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }

        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task must have a valid status!");
        }

        Task existingTask = taskRepository.findByProjectIdAndId(projectId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task ID does not exist!"));

        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID projectId, UUID taskId) {
        taskRepository.deleteByProjectIdAndId(projectId, taskId);
    }
}
