package com.nlmb.project_management_backend.mappers;

import com.nlmb.project_management_backend.domain.dto.TaskDto;
import com.nlmb.project_management_backend.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
