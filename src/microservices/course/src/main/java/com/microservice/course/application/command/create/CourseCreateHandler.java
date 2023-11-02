package com.microservice.course.application.command.create;

import com.microservice.course.application.http.CourseCommandResponse;
import com.microservice.course.domain.*;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CourseCreateHandler implements IRequestHandler<CourseCreateCommand, CourseCommandResponse> {

    private final ICourseService service;

    public CourseCreateHandler(ICourseService service) {
        this.service = service;
    }

    @Override
    public CourseCommandResponse handle(CourseCreateCommand request) {
        try{
            Course course = new Course(
                    new CourseId(UUID.randomUUID()),
                    new CourseName(request.getName()),
                    new CourseDescription(request.getDescription()),
                    CourseState.ACTIVE);
            service.create(course);
            return new CourseCommandResponse(course.id().value(), "Course created!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
