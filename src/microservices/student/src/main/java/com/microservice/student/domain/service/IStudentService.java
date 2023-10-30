package com.microservice.student.domain.service;

import com.microservice.student.domain.Student;
import com.microservice.utils.core.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    void create(Student student);

    void update(Student student);

    void delete(UUID id);

    Student findById(UUID id);

    List<Student> findAll();

    boolean existByEmail(String email);

    PaginatedResponse findAll(Pageable pageable);
}
