package com.microservice.student.infrastructure.adapter.query;

import com.microservice.student.domain.Student;
import com.microservice.student.domain.repository.query.IStudentQueryRepository;
import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresDBStudentQueryRepository implements IStudentQueryRepository {

    private final IStudentReadDataJpaRepository repository;

    public PostgresDBStudentQueryRepository(IStudentReadDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student findById(UUID id) {
        Optional<StudentDto> optional = repository.findById(id);

        if (optional.isEmpty()){
            throw new RuntimeException("Student not found!");
        }

        return optional.get().toAggregate();
    }

    @Override
    public List<Student> findAll() {
        List<StudentDto> dtoList = repository.findAll();
        List<Student> studentList = new ArrayList<>();
        dtoList.forEach(x -> studentList.add(x.toAggregate()));

        return studentList;
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
