package com.microservice.student.infrastructure.adapter.query;

import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStudentReadDataJpaRepository extends JpaRepository<StudentDto, UUID> {

    boolean existsByEmail(String email);

    @Override
    @Query(value = "FROM StudentDto t WHERE t.id=?1 AND t.state='ACTIVE'")
    Optional<StudentDto> findById(UUID uuid);
}
