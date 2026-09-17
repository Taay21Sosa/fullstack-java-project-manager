package com.nlmb.project_management_backend.services;

import com.nlmb.project_management_backend.domain.entities.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {

    List<Project> listAllProjects();
    Project createProject(Project project);
    Optional<Project> getProjectById(UUID id);
    Project updateProject(UUID projectId, Project project);
    void deleteProject(UUID projectId);
}
