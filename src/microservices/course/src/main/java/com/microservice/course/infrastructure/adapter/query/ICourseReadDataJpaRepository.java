package com.microservice.course.infrastructure.adapter.query;

import com.microservice.course.infrastructure.entities.hibernate.CourseDto;
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
public interface ICourseReadDataJpaRepository extends JpaRepository<CourseDto, UUID> , JpaSpecificationExecutor<CourseDto>{

    @Override
    @Query(value = "FROM CourseDto t WHERE t.id=?1 AND t.state='ACTIVE'")
    Optional<CourseDto> findById(@NonNull UUID uuid);

    @Override
    Page<CourseDto> findAll(@NonNull Pageable pageable);

    @Override
    Page<CourseDto> findAll(@NonNull Specification spec, @NonNull Pageable pageable);

}
