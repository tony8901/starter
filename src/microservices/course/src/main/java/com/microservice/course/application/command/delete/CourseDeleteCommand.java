package com.microservice.course.application.command.delete;

import com.microservice.utils.core.http.IRequest;

import java.util.UUID;

public record CourseDeleteCommand(UUID id) implements IRequest {
}
