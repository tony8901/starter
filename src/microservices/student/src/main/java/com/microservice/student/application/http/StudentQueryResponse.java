package com.microservice.student.application.http;

import com.microservice.utils.core.IRequestResponse;

public record StudentQueryResponse(
        long id,
        String name,
        String email,
        long courseId) implements IRequestResponse {
}
