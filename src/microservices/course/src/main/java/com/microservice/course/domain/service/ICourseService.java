package com.microservice.course.domain.service;

import com.microservice.course.domain.*;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICourseService {

    void create(Course course);

    void update(Course course);

    void delete(Course course);

    List<Course> findAll();

    Course findById(CourseId id);

    PaginatedResponse findAllPaginatedFilter(Pageable pageable, CourseName name, CourseDescription description, CourseState state);

}
