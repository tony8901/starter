package com.microservice.course.domain.repository.command;

import com.microservice.course.domain.Course;

public interface ICourseCommandRepository {

    void create(Course course);

    void update(Course course);

    void delete(Course course);
}
