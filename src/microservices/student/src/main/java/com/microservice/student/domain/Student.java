package com.microservice.student.domain;

import java.util.List;
import java.util.UUID;

public record Student(
        UUID id,
        String name,
        String email,
        List<UUID> courses,
        StudentState state) {

}
