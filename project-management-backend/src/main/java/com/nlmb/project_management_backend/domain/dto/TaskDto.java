package com.nlmb.project_management_backend.domain.dto;

import com.nlmb.project_management_backend.domain.entities.Priority;
import com.nlmb.project_management_backend.domain.entities.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String name,
        String description,
        LocalDateTime dueDate,
        Status status,
        Priority priority
) {
}
