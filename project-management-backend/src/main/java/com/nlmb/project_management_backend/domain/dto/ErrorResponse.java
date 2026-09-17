package com.nlmb.project_management_backend.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
