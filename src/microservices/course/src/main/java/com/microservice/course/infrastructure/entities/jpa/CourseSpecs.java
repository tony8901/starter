package com.microservice.course.infrastructure.entities.jpa;

import com.microservice.course.infrastructure.entities.hibernate.CourseDto;
import com.microservice.course.infrastructure.entities.hibernate.CourseDto_;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecs {

    public static Specification<CourseDto> getNameContainingIgnoreCase(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(
                root.get(CourseDto_.NAME)), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<CourseDto> getDescriptionContainingIgnoreCase(String description){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(
                root.get(CourseDto_.DESCRIPTION)), "%" + description.toLowerCase() + "%"));
    }

    public static Specification<CourseDto> getStateEqual(String state){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(
                root.get(CourseDto_.STATE)), state.toLowerCase()));
    }
}
