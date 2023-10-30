package com.microservice.student.application.query.findall;

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

    public FindAllStudentsResponse handle() {
        List<StudentQueryResponse> responseList = new ArrayList<>();
        try{
            List<Student> studentList = service.findAll();
            studentList.forEach(x -> responseList.add(new StudentQueryResponse(
                    x.id(), x.name(), x.email(), x.courses())));
        } catch (Exception e){
            throw new RuntimeException("Something is wrong!: "+e.getMessage());
        }
        return new FindAllStudentsResponse(responseList);
    }
}
