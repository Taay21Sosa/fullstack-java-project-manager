package com.nlmb.project_management_backend.mappers.impl;

import com.nlmb.project_management_backend.domain.dto.ProjectDto;
import com.nlmb.project_management_backend.domain.entities.Project;
import com.nlmb.project_management_backend.domain.entities.Status;
import com.nlmb.project_management_backend.domain.entities.Task;
import com.nlmb.project_management_backend.mappers.ProjectMapper;
import com.nlmb.project_management_backend.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    private final TaskMapper taskMapper;

    public ProjectMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public Project fromDto(ProjectDto projectDto) {
        return new Project(
                projectDto.id(),
                projectDto.name(),
                projectDto.description(),
                Optional.ofNullable(projectDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                projectDto.startDate(),
                projectDto.endDate(),
                projectDto.status(),
                projectDto.priority(),
                null,
                null
        );
    }

    @Override
    public ProjectDto toDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                Optional.ofNullable(project.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateProjectProgress(project.getTasks()),
                Optional.ofNullable(project.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList()
                        ).orElse(null),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus(),
                project.getPriority()
        );
    }

    private Double calculateProjectProgress(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }

        long completedTaskCount = tasks.stream().filter(task ->
                task.getStatus() == Status.COMPLETED ||
                task.getStatus() == Status.CANCELED
        ).count();

        return completedTaskCount / (double) tasks.size();
    }
}
