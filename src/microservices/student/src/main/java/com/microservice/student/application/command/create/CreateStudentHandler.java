package com.microservice.student.application.command.create;

import com.microservice.student.application.http.StudentCommandResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.StudentState;
import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.IRequestHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CreateStudentHandler
        implements IRequestHandler<CreateStudentCommand, StudentCommandResponse> {

    private final IStudentService studentService;

    public CreateStudentHandler(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public StudentCommandResponse handle(CreateStudentCommand command) {
        StudentCommandResponse response = new StudentCommandResponse();

        try {
            if (studentService.existByEmail(command.getEmail())){
                throw new RuntimeException("Email is already in use!");
            }

            Student student = new Student(
                    UUID.randomUUID(), command.getName(), command.getEmail(),
                    command.getCourses(), StudentState.ACTIVE);
            studentService.create(student);
            response.setError(false);
            response.setId(student.id().toString());
            response.setMessage("Student created!");
        } catch (Exception e){
            response.setError(true);
            response.setMessage("Something is wrong: "+e.getMessage());
        }
        return response;
    }
}
