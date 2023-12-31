package com.microservice.student.application.command.update;

import com.microservice.utils.core.http.IRequest;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class UpdateStudentCommand implements IRequest {

    private final UUID id;

    private final String name;

    private final Set<UUID> courses;

    private UpdateStudentCommand(UUID id, String name, Set<UUID> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public static UpdateStudentCommand fromRequest(UpdateStudentRequest request){
        return new UpdateStudentCommand(request.id(), request.name(), request.courses());
    }
}
