package com.microservice.student.application.command.update;

import java.util.List;
import java.util.UUID;

public record UpdateStudentRequest(UUID id, String name, List<UUID> courses) {
}
