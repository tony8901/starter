package com.microservice.course.application.query.findstudents;

import com.microservice.course.application.http.StudentResponse;
import com.microservice.course.application.http.StudentsAllResponse;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import com.microservice.utils.feign.student.http.FeignStudentResponse;
import com.microservice.utils.feign.student.service.IFeignStudentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseFindStudentsHandler
        implements IRequestHandler<CourseFindStudentsQuery, StudentsAllResponse> {

    private final ICourseService service;

    private final IFeignStudentService feignStudentService;

    public CourseFindStudentsHandler(ICourseService service, IFeignStudentService feignStudentService) {
        this.service = service;
        this.feignStudentService = feignStudentService;
    }

    @Override
    public StudentsAllResponse handle(CourseFindStudentsQuery request) {
        List<FeignStudentResponse> students = feignStudentService.getStudentsByCourseId(request.id());
        List<StudentResponse> responses = new ArrayList<>();
        students.forEach(x -> responses.add(new StudentResponse(x.name(), x.email())));

        return new StudentsAllResponse(responses);
    }
}
