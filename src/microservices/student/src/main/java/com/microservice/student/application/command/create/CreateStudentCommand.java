package com.microservice.student.application.command.create;

import com.microservice.utils.core.IRequest;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateStudentCommand implements IRequest{

    private final String name;

    private final String email;

    private final UUID courseId;

    private CreateStudentCommand(String name, String email, UUID courseId) {
        this.name = name;
        this.email = email;
        this.courseId = courseId;
    }

    public static CreateStudentCommand fromRequest(CreateStudentRequest request){
        return new CreateStudentCommand(request.name(), request.email(), request.courseId());
    }

}
