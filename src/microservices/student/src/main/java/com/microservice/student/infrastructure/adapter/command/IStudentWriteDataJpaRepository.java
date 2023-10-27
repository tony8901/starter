package com.microservice.student.infrastructure.adapter.command;

import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentWriteDataJpaRepository extends JpaRepository<StudentDto, UUID>{
}
