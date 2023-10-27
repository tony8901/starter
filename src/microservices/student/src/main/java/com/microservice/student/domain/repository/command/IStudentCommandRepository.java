package com.microservice.student.domain.repository.command;

import com.microservice.student.domain.Student;

public interface IStudentCommandRepository {
    void create(Student student);

    void update(Student student);

    void delete(Student student);
}
