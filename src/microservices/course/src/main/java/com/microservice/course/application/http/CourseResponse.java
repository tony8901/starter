package com.microservice.course.application.http;

import com.microservice.course.domain.Course;
import com.microservice.utils.core.http.IResponse;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CourseResponse implements IResponse {
    private final UUID id;

    private final String name;

    private final String description;

    private final String state;

    public CourseResponse(Course course) {
        this.id = course.id().value();
        this.name = course.name().value();
        this.description = course.description().value();
        this.state = course.state().name();
    }
}
