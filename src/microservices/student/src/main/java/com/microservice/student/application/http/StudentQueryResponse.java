package com.microservice.student.application.http;

import com.microservice.utils.core.IRequestResponse;

import java.util.List;
import java.util.UUID;

public record StudentQueryResponse(
        UUID id,
        String name,
        String email,
        List<UUID> courses) implements IRequestResponse {
}
