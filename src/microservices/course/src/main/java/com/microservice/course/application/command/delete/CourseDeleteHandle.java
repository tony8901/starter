package com.microservice.course.application.command.delete;

import com.microservice.course.application.http.CourseCommandResponse;
import com.microservice.course.domain.Course;
import com.microservice.course.domain.CourseId;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class CourseDeleteHandle implements IRequestHandler<CourseDeleteCommand, CourseCommandResponse> {

    private final ICourseService service;

    public CourseDeleteHandle(ICourseService service) {
        this.service = service;
    }

    @Override
    public CourseCommandResponse handle(CourseDeleteCommand request) {
        try {
            Course toDelete = service.findById(new CourseId(request.id()));
            service.delete(toDelete);
            return new CourseCommandResponse(request.id(), "Course deleted!");
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
