package com.microservice.course.application.command.update;

import com.microservice.utils.core.http.IRequest;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CourseUpdateCommand implements IRequest {

    private final UUID id;

    private final String name;

    private final String description;

    private CourseUpdateCommand(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static CourseUpdateCommand fromRequest(CourseUpdateRequest request){
        return new CourseUpdateCommand(request.id(), request.name(), request.description());
    }
}
