package com.microservice.course.application.command.create;

import com.microservice.utils.core.http.IRequest;
import lombok.Getter;

@Getter
public class CourseCreateCommand implements IRequest {
    private final String name;

    private final String description;

    private CourseCreateCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static CourseCreateCommand fromRequest(CourseCreateRequest request){
        return new CourseCreateCommand(request.name(), request.description());
    }
}
