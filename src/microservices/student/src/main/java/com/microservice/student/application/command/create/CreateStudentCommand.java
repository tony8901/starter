package com.microservice.student.application.command.create;

import com.microservice.utils.core.http.IRequest;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class CreateStudentCommand implements IRequest{

    private final String name;

    private final String email;

    private final Set<UUID> courses;

    private CreateStudentCommand(String name, String email, Set<UUID> courses) {
        this.name = name;
        this.email = email;
        this.courses = courses;
    }

    public static CreateStudentCommand fromRequest(CreateStudentRequest request){
        return new CreateStudentCommand(request.name(), request.email(), request.courses());
    }

}
