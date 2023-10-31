package com.microservice.student.application.command.update;

import java.util.Set;
import java.util.UUID;

public record UpdateStudentRequest(UUID id, String name, Set<UUID> courses) {
}
