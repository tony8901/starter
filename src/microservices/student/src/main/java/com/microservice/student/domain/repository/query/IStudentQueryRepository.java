package com.microservice.student.domain.repository.query;

import com.microservice.student.domain.Student;
import com.microservice.utils.core.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IStudentQueryRepository {

    Student findById(UUID id);

    List<Student> findAll();

    boolean existByEmail(String email);

    PaginatedResponse findAll(Pageable pageable);
}
