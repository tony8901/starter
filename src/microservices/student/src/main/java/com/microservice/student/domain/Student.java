package com.microservice.student.domain;

import java.util.UUID;

public record Student(
        UUID id,
        String name,
        String email,
        UUID courseId,
        StudentState state) {

}
