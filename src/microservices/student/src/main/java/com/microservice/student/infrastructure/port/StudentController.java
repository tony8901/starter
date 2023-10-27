package com.microservice.student.infrastructure.port;

import com.microservice.student.application.command.create.CreateStudentCommand;
import com.microservice.student.application.command.create.CreateStudentHandler;
import com.microservice.student.application.command.create.CreateStudentRequest;
import com.microservice.student.application.http.StudentCommandResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final CreateStudentHandler createHandler;

    public StudentController(CreateStudentHandler createHandler) {
        this.createHandler = createHandler;
    }

    @PostMapping
    public ResponseEntity<StudentCommandResponse> create(@RequestBody CreateStudentRequest request){
        CreateStudentCommand command = CreateStudentCommand.fromRequest(request);
        StudentCommandResponse response = createHandler.handle(command);

        return ResponseEntity.ok(response);
    }
}
