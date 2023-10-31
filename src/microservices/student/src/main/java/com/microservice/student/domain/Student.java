package com.microservice.student.domain;

import java.util.Set;
import java.util.UUID;

public record Student(
        UUID id,
        String name,
        String email,
        Set<UUID> courses,
        StudentState state) {

}
