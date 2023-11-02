package com.microservice.course.application.command.create;

public record CourseCreateRequest(
        String name,
        String description
) {
}
