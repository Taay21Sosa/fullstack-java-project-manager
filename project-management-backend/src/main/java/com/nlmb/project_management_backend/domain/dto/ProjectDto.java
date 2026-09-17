package com.nlmb.project_management_backend.domain.dto;

import com.nlmb.project_management_backend.domain.entities.Priority;
import com.nlmb.project_management_backend.domain.entities.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        String name,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Status status,
        Priority priority
) {
}
