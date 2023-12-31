package com.microservice.student.application.http;

import com.microservice.student.domain.Student;
import com.microservice.utils.core.http.IResponse;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class StudentQueryResponse implements IResponse {
    private final UUID id;
    private final String name;
    private final String email;
    private final Set<UUID> courses;

    public StudentQueryResponse(Student student) {
        this.id = student.id();
        this.name = student.name();
        this.email = student.email();
        this.courses = student.courses();
    }
}
