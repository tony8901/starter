package com.microservice.student.domain.repository.query;

import com.microservice.student.domain.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentQueryRepository {

    Student findById(UUID id);

    List<Student> findAll();

    boolean existByEmail(String email);
}
