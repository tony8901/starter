package com.microservice.student.application.command.update;

import com.microservice.student.application.http.StudentCommandResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentHandler
        implements IRequestHandler<UpdateStudentCommand, StudentCommandResponse> {

    private final IStudentService service;

    public UpdateStudentHandler(IStudentService service) {
        this.service = service;
    }

    @Override
    public StudentCommandResponse handle(UpdateStudentCommand command) {
        StudentCommandResponse response = new StudentCommandResponse();

        try {
            Student student = service.findById(command.getId());
            Student toUpdate = new Student(
                    student.id(), command.getName(),
                    student.email(), command.getCourses(), student.state());
            service.update(toUpdate);
            response.setId(toUpdate.id().toString());
            response.setMessage("Student updated!");
            response.setError(false);
        } catch (Exception e){
            response.setError(true);
            response.setMessage("Something is wrong: "+e.getMessage());
        }
        return response;
    }
}
