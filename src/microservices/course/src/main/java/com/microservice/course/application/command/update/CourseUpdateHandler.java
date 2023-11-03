package com.microservice.course.application.command.update;

import com.microservice.course.application.http.CourseCommandResponse;
import com.microservice.course.domain.Course;
import com.microservice.course.domain.CourseDescription;
import com.microservice.course.domain.CourseId;
import com.microservice.course.domain.CourseName;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class CourseUpdateHandler implements IRequestHandler<CourseUpdateCommand, CourseCommandResponse> {

    private final ICourseService service;

    public CourseUpdateHandler(ICourseService service) {
        this.service = service;
    }

    @Override
    public CourseCommandResponse handle(CourseUpdateCommand request) {
        try {
            Course course = service.findById(new CourseId(request.getId()));
            Course toUpdate = new Course(
                    course.id(), new CourseName(request.getName()),
                    new CourseDescription(request.getDescription()),
                    course.state());
            service.update(toUpdate);
            return new CourseCommandResponse(request.getId(), "Course updated!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
