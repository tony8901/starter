package com.microservice.course.application.http;

import com.microservice.utils.core.http.IResponse;

import java.util.UUID;

public record CourseCommandResponse(
        UUID id,
        String message) implements IResponse {
}
