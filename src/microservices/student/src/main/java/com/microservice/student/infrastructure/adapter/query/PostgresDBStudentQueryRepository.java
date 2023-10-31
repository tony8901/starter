package com.microservice.student.infrastructure.adapter.query;

import com.microservice.student.application.http.StudentQueryResponse;
import com.microservice.student.domain.Student;
import com.microservice.student.domain.repository.query.IStudentQueryRepository;
import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import com.microservice.student.infrastructure.repository.jpa.SpecStudent;
import com.microservice.utils.core.PaginatedResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Student> findAllPaginated() {
        List<StudentDto> dtoList = repository.findAll();
        List<Student> studentList = new ArrayList<>();
        dtoList.forEach(x -> studentList.add(x.toAggregate()));

        return studentList;
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public PaginatedResponse findAllPaginated(Pageable pageable) {
        Page<StudentDto> dtoPage = repository.findAll(pageable);
        List<StudentQueryResponse> responseList = new ArrayList<>();
        dtoPage.forEach(x -> responseList.add(new StudentQueryResponse(x.toAggregate())));

        return new PaginatedResponse(
                "ok", responseList, dtoPage.getTotalPages(),dtoPage.getNumberOfElements(),
                dtoPage.getTotalElements(), dtoPage.getSize(), dtoPage.getNumber());
    }

    @Override
    public PaginatedResponse findAllPaginatedFilter(Pageable pageable, String filter, String name, String email) {
        List<Specification<StudentDto>> specs = new ArrayList<>();

        if (StringUtils.isNotBlank(filter)){
            specs.add(Specification.anyOf(
                    SpecStudent.getEmailEqual(filter),
                    SpecStudent.getNameContainingIgnoreCase(filter)
            ));
        }

        if (StringUtils.isNotBlank(name)){
            specs.add(SpecStudent.getNameContainingIgnoreCase(name));
        }

        if (StringUtils.isNotBlank(email)){
            specs.add(SpecStudent.getEmailEqual(email));
        }

        Page<StudentDto> dtoPage = repository.findAll(Specification.allOf(specs), pageable);

        List<StudentQueryResponse> responseList = new ArrayList<>();
        dtoPage.forEach(x -> responseList.add(new StudentQueryResponse(x.toAggregate())));

        return new PaginatedResponse(
                "ok", responseList, dtoPage.getTotalPages(),dtoPage.getNumberOfElements(),
                dtoPage.getTotalElements(), dtoPage.getSize(), dtoPage.getNumber());
    }

    @Override
    public List<Student> findAllByCourseId(UUID courseId) {
        List<StudentDto> dtoList = repository.findAll(SpecStudent.getCourseIsMember(courseId));
        List<Student> studentList = new ArrayList<>();
        dtoList.forEach(x -> studentList.add(x.toAggregate()));
        return studentList;
    }
}
