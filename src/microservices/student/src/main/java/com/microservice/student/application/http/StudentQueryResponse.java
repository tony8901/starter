package com.microservice.student.application.http;

import com.microservice.student.domain.Student;
import com.microservice.utils.core.IRequestResponse;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class StudentQueryResponse implements IRequestResponse {
    private final UUID id;
    private final String name;
    private final String email;
    private final List<UUID> courses;

    public StudentQueryResponse(Student student) {
        this.id = student.id();
        this.name = student.name();
        this.email = student.email();
        this.courses = student.courses();
    }
}
