package com.microservice.student.infrastructure.repository.jpa;

import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import com.microservice.student.infrastructure.repository.hibernate.StudentDto_;
import jakarta.persistence.criteria.SetJoin;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class SpecStudent {

    public static Specification<StudentDto> getNameContainingIgnoreCase(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(
                root.get(StudentDto_.NAME)), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<StudentDto> getEmailEqual(String email){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(StudentDto_.EMAIL), email));
    }

    public static Specification<StudentDto> getCourseIsMember(UUID courseId){
        return ((root, query, criteriaBuilder) ->{
            SetJoin<StudentDto, UUID> courses = root.joinSet(StudentDto_.COURSES);
            return criteriaBuilder.equal(courses, courseId);
        });
    }
}
