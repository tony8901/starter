package com.microservice.student.infrastructure.repository.hibernate;

import com.microservice.student.domain.Student;
import com.microservice.student.domain.StudentState;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;
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

    @ElementCollection
    @Column(name = "courses")
    private Set<UUID> courses;

    @Column(nullable = false)
    private String state;

    public StudentDto() {
    }

    public StudentDto(Student student){
        this.id = student.id();
        this.name = student.name();
        this.email = student.email();
        this.courses = student.courses();
        this.state = student.state().name();
    }

    public Student toAggregate(){
        return new Student(getId(), getName(), getEmail(), getCourses(), StudentState.valueOf(getState()));
    }
}
