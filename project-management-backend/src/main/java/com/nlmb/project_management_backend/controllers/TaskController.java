package com.nlmb.project_management_backend.controllers;

import com.nlmb.project_management_backend.domain.dto.TaskDto;
import com.nlmb.project_management_backend.domain.entities.Task;
import com.nlmb.project_management_backend.mappers.TaskMapper;
import com.nlmb.project_management_backend.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task/{project_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService,  TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    // ----- GET request
    @GetMapping
    public List<TaskDto> getAllTasks(@PathVariable("project_id") UUID projectId) {
        return taskService.getAllTasks(projectId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{project_id}")
    public Optional<TaskDto> getTask(
            @PathVariable("project_id") UUID projectId,
            @PathVariable("task_id") UUID taskId
    ) {
        return taskService.getTask(projectId, taskId)
                .map(taskMapper::toDto);
    }

    // ----- POST request
    @PostMapping
    public TaskDto createTask(
            @PathVariable("project_id") UUID projectId,
            @RequestBody TaskDto taskDto
    ) {
        Task newTask = taskService.createTask(
                projectId,
                taskMapper.fromDto(taskDto)
        );

        return taskMapper.toDto(newTask);
    }

    // ----- PUT request
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("project_id") UUID projectId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ) {
        Task updatedTask = taskService.updateTask(
                projectId, taskId, taskMapper.fromDto(taskDto)
        );

        return taskMapper.toDto(updatedTask);
    }

    // ----- DELETE request
    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("project_id") UUID projectId,
            @PathVariable("task_id") UUID taskId
    ) {
        taskService.deleteTask(projectId, taskId);
    }

}
