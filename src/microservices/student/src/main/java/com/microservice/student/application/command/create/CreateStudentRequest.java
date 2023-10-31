package com.microservice.student.application.command.create;

import java.util.Set;
import java.util.UUID;

public record CreateStudentRequest(
        String name,
        String email,
        Set<UUID> courses) {
}
