package com.microservice.student.domain.repository.query;

import com.microservice.student.domain.Student;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IStudentQueryRepository {

    Student findById(UUID id);

    List<Student> findAll();

    boolean existByEmail(String email);

    PaginatedResponse findAllPaginated(Pageable pageable);

    PaginatedResponse findAllPaginatedFilter(Pageable pageable, String filter, String name, String email);

    List<Student> findAllByCourseId(UUID courseId);
}
