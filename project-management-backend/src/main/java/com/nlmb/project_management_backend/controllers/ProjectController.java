package com.nlmb.project_management_backend.controllers;

import com.nlmb.project_management_backend.domain.dto.ProjectDto;
import com.nlmb.project_management_backend.domain.entities.Project;
import com.nlmb.project_management_backend.mappers.ProjectMapper;
import com.nlmb.project_management_backend.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    // ----- GET requests
    @GetMapping
    public List<ProjectDto> listAllProjects() {
        return projectService.listAllProjects()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{project_id}")
    public Optional<ProjectDto> getProjectById(@PathVariable("project_id") UUID projectId) {
        return projectService.getProjectById(projectId)
                .map(projectMapper::toDto);
    }

    // ----- POST requests
    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        Project newProject = projectService.createProject(
                projectMapper.fromDto(projectDto)
        );

        return projectMapper.toDto(newProject);
    }

    // ----- PUT request
    @PutMapping(path = "/{project_id}")
    public ProjectDto updateProject(
            @PathVariable("project_id") UUID projectId,
            @RequestBody ProjectDto projectDto
    ) {
        Project updatedProject = projectService.updateProject(
                projectId,
                projectMapper.fromDto(projectDto)
        );

        return projectMapper.toDto(updatedProject);
    }

    // ------ DELETE request
    @DeleteMapping(path = "/{project_id}")
    public void deleteProject(@PathVariable("project_id") UUID projectId) {
        projectService.deleteProject(projectId);
    }
}
