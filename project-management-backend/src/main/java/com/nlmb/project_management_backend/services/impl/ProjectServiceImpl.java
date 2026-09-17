package com.nlmb.project_management_backend.services.impl;

import com.nlmb.project_management_backend.domain.entities.Project;
import com.nlmb.project_management_backend.repositories.ProjectRepository;
import com.nlmb.project_management_backend.services.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> listAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        if (project.getId() != null) {
            throw new IllegalArgumentException("Project already has an ID!");
        }

        if (project.getName() == null || project.getName().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty!");
        }

        LocalDateTime now = LocalDateTime.now();

        return projectRepository.save(
                new Project(
                        null,
                        project.getName(),
                        project.getDescription(),
                        null,
                        project.getStartDate(),
                        project.getEndDate(),
                        project.getStatus(),
                        project.getPriority(),
                        now,
                        now
                )
        );
    }

    @Override
    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project updateProject(UUID projectId, Project project) {
        if (project.getId() == null) {
            throw new IllegalArgumentException("Project ID cannot be empty!");
        }

        if (!Objects.equals(project.getId(), projectId)) {
            throw new IllegalArgumentException("Attempting to change the project ID, this is not permitted!");
        }

        Project existingProject = projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalArgumentException("Project ID does not exist!")
        );

        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setStatus(project.getStatus());
        existingProject.setPriority(project.getPriority());

        return  projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }
}
