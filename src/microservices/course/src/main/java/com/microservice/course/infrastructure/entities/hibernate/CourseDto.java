package com.microservice.course.infrastructure.entities.hibernate;

import com.microservice.course.domain.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "jpa_course")
@Getter
public class CourseDto {

    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String state;

    public CourseDto() {
    }

    public CourseDto(Course course) {
        this.id = course.id() != null ? course.id().value() : null;
        this.name = course.name() != null ? course.name().value() : null;
        this.description = course.description() != null ? course.description().value() : null;
        this.state = course.state() != null ? course.state().name() : null;
    }

    public Course toAgregate(){
        CourseId nId = new CourseId(getId());
        CourseName nName = new CourseName(getName());
        CourseDescription nDescription = new CourseDescription(getDescription());
        CourseState nState = CourseState.valueOf(getState());
        return new Course(nId, nName, nDescription, nState);
    }
}
