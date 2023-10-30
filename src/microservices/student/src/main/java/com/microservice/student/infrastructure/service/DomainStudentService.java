package com.microservice.student.infrastructure.service;

import com.microservice.student.domain.Student;
import com.microservice.student.domain.repository.command.IStudentCommandRepository;
import com.microservice.student.domain.repository.query.IStudentQueryRepository;
import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DomainStudentService implements IStudentService {

    private final IStudentCommandRepository commandRepository;

    private final IStudentQueryRepository queryRepository;

    public DomainStudentService(IStudentCommandRepository commandRepository, IStudentQueryRepository queryRepository) {
        this.commandRepository = commandRepository;
        this.queryRepository = queryRepository;
    }

    @Override
    public void create(Student student) {
        commandRepository.create(student);
    }

    @Override
    public void update(Student student) {
        commandRepository.update(student);
    }

    @Override
    public void delete(UUID id) {
        Student toDelete = queryRepository.findById(id);
        commandRepository.delete(toDelete);
    }

    @Override
    public Student findById(UUID id) {
        return queryRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return queryRepository.findAll();
    }

    @Override
    public boolean existByEmail(String email) {
        return queryRepository.existByEmail(email);
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        return queryRepository.findAll(pageable);
    }
}
