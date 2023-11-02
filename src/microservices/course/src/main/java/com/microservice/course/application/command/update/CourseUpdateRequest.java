package com.microservice.course.application.command.update;

import java.util.UUID;

public record CourseUpdateRequest(UUID id, String name, String description) {

}
