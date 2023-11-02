package com.microservice.student.infrastructure.adapter.query;

import com.microservice.student.infrastructure.repository.hibernate.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStudentReadDataJpaRepository extends JpaRepository<StudentDto, UUID>, JpaSpecificationExecutor<StudentDto> {

    boolean existsByEmail(String email);

    @Override
    @NonNull
    @Query(value = "FROM StudentDto t WHERE t.id=?1 AND t.state='ACTIVE'")
    Optional<StudentDto> findById(@NonNull UUID uuid);

    @Override
    @NonNull
    Page<StudentDto> findAll(@NonNull Pageable pageable);

    @Override
    @NonNull
    Page<StudentDto> findAll(@NonNull Specification spec, @NonNull Pageable pageable);
}
