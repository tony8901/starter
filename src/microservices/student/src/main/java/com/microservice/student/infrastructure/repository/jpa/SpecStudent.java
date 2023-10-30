package com.microservice.student.infrastructure.repository.jpa;

import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import com.microservice.student.infrastructure.repository.hibernate.StudentDto_;
import org.springframework.data.jpa.domain.Specification;

public class SpecStudent {

    public static Specification<StudentDto> getNameContainingIgnoreCase(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(
                root.get(StudentDto_.NAME)), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<StudentDto> getEmailEqual(String email){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(StudentDto_.EMAIL), email));
    }
}
