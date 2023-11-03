package com.microservice.course.application.query.findstudents;

import com.microservice.utils.core.http.IRequest;

import java.util.UUID;

public record CourseFindStudentsQuery(
        UUID id) implements IRequest {
}
