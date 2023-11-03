package com.microservice.course.application.query.findall;

import com.microservice.course.application.http.CourseAllQueryResponse;
import com.microservice.course.application.http.CourseResponse;
import com.microservice.course.domain.Course;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseFindAllHandler
        implements IRequestHandler<CourseFindAllQuery, CourseAllQueryResponse> {

    private final ICourseService service;

    public CourseFindAllHandler(ICourseService service) {
        this.service = service;
    }

    @Override
    public CourseAllQueryResponse handle(CourseFindAllQuery request) {
        try{
            List<Course> courses = service.findAll();
            List<CourseResponse> responses = new ArrayList<>();
            courses.forEach(x -> responses.add(
                    new CourseResponse(x)));
            return new CourseAllQueryResponse(responses);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
