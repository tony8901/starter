package com.microservice.student.application.command.delete;

import com.microservice.student.application.http.StudentCommandResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.http.IRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentHandler
        implements IRequestHandler<DeleteStudentCommand, StudentCommandResponse> {

    private final IStudentService service;

    public DeleteStudentHandler(IStudentService service) {
        this.service = service;
    }

    @Override
    public StudentCommandResponse handle(DeleteStudentCommand command) {
        StudentCommandResponse response = new StudentCommandResponse();

        try {
            Student student = service.findById(command.id());
            service.delete(student.id());
            response.setError(false);
            response.setId(command.id().toString());
            response.setMessage("Student deleted!");
        } catch (Exception e){
            response.setError(true);
            response.setMessage("Something is wrong: "+e.getMessage());
        }
        return response;
    }
}
