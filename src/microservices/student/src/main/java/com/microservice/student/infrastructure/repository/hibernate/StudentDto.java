package com.microservice.student.infrastructure.repository.hibernate;

import com.microservice.student.domain.Student;
import com.microservice.student.domain.StudentState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "jpa_student")
@Getter
public class StudentDto {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "course_id")
    private UUID courseId;

    @Column(nullable = false)
    private String state;

    public StudentDto() {
    }

    public StudentDto(Student student){
        this.id = student.id();
        this.name = student.name();
        this.email = student.email();
        this.courseId = student.courseId();
        this.state = student.state().name();
    }

    public Student toAggregate(){
        return new Student(getId(), getName(), getEmail(), getCourseId(), StudentState.valueOf(getState()));
    }
}
