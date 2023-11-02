package com.microservice.student.application.query.getbycourseid;

import com.microservice.student.application.http.AllStudentsResponse;
import com.microservice.student.application.http.StudentQueryResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindByCourseIdHandler
        implements IRequestHandler<FindByCourseIdQuery, AllStudentsResponse> {

    private final IStudentService service;

    public FindByCourseIdHandler(IStudentService service) {
        this.service = service;
    }

    @Override
    public AllStudentsResponse handle(FindByCourseIdQuery request) {
        try{
            List<StudentQueryResponse> responseList = new ArrayList<>();
            List<Student> studentList = service.findAllByCourseId(request.getCourseId());
            studentList.forEach(x -> responseList.add(new StudentQueryResponse(x)));
            return new AllStudentsResponse(responseList);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
