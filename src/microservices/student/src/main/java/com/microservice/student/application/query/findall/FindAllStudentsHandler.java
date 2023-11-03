package com.microservice.student.application.query.findall;

import com.microservice.student.application.http.AllStudentsResponse;
import com.microservice.student.application.http.StudentQueryResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.service.IStudentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllStudentsHandler {

    private final IStudentService service;

    public FindAllStudentsHandler(IStudentService service) {
        this.service = service;
    }

    public AllStudentsResponse handle() {
        List<StudentQueryResponse> responseList = new ArrayList<>();
        try{
            List<Student> studentList = service.findAll();
            studentList.forEach(x -> responseList.add(new StudentQueryResponse(x)));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return new AllStudentsResponse(responseList);
    }
}
