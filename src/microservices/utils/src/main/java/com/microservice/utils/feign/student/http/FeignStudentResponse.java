package com.microservice.utils.feign.student.http;

import java.util.Set;
import java.util.UUID;

public record FeignStudentResponse(
        UUID id,
        String name,
        String email,
        Set<UUID> courses) {
}
