package com.microservice.student.application.command.create;

import java.util.List;
import java.util.UUID;

public record CreateStudentRequest(
        String name,
        String email,
        List<UUID> courses) {
}
