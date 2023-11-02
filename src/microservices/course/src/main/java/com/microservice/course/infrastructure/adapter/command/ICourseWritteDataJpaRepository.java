package com.microservice.course.infrastructure.adapter.command;

import com.microservice.course.infrastructure.entities.hibernate.CourseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICourseWritteDataJpaRepository extends JpaRepository<CourseDto, UUID> {
}
