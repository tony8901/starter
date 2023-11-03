package com.microservice.course.domain;

public record Course(
        CourseId id,
        CourseName name,
        CourseDescription description,
        CourseState state) {
}
