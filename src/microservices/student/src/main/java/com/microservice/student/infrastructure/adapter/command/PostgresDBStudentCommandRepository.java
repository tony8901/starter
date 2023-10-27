package com.microservice.student.infrastructure.adapter.command;

import com.microservice.student.domain.Student;
import com.microservice.student.domain.StudentState;
import com.microservice.student.domain.repository.command.IStudentCommandRepository;
import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class PostgresDBStudentCommandRepository implements IStudentCommandRepository {

    private final IStudentWriteDataJpaRepository repository;

    public PostgresDBStudentCommandRepository(IStudentWriteDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Student student) {
        repository.save(new StudentDto(student));
    }

    @Override
    public void update(Student student) {
        repository.save(new StudentDto(student));
    }

    @Override
    public void delete(Student student) {
        Student toDelete = new Student(
                student.id(), student.name(), student.email(),
                student.courseId(), StudentState.INACTIVE);
        repository.save(new StudentDto(toDelete));
    }
}
