package com.nlmb.project_management_backend.mappers;

import com.nlmb.project_management_backend.domain.dto.ProjectDto;
import com.nlmb.project_management_backend.domain.dto.TaskDto;
import com.nlmb.project_management_backend.domain.entities.Project;
import com.nlmb.project_management_backend.domain.entities.Task;

public interface ProjectMapper {

    Project fromDto(ProjectDto projectDto);

    ProjectDto toDto(Project project);
}
