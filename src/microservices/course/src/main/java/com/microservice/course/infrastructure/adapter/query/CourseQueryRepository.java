package com.microservice.course.infrastructure.adapter.query;

import com.microservice.course.application.http.CourseResponse;
import com.microservice.course.domain.*;
import com.microservice.course.domain.repository.query.ICourseQueryRepository;
import com.microservice.course.infrastructure.entities.hibernate.CourseDto;
import com.microservice.course.infrastructure.entities.jpa.CourseSpecs;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CourseQueryRepository implements ICourseQueryRepository {

    private final ICourseReadDataJpaRepository repository;

    public CourseQueryRepository(ICourseReadDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> findAll() {
        List<CourseDto> dtos = repository.findAll();
        List<Course> courses = new ArrayList<>();
        dtos.forEach(x -> courses.add(x.toAgregate()));
        return courses;
    }

    @Override
    public Course findById(CourseId id) {
        Optional<CourseDto> optional = repository.findById(id.value());

        if (optional.isEmpty()){
            throw new RuntimeException("Course not found!");
        }

        return optional.get().toAgregate();
    }

    @Override
    public PaginatedResponse findAllPaginatedFilter(Pageable pageable, CourseName name, CourseDescription description, CourseState state) {
        List<Specification<CourseDto>> specs = new ArrayList<>();

        if (Objects.nonNull(name)){
            specs.add(CourseSpecs.getNameContainingIgnoreCase(name.value()));
        }

        if (Objects.nonNull(description)) {
            specs.add(CourseSpecs.getDescriptionContainingIgnoreCase(description.value()));
        }

        if (Objects.nonNull(state)){
            specs.add(CourseSpecs.getStateEqual(state.name()));
        }

        Page<CourseDto> page = repository.findAll(Specification.allOf(specs), pageable);
        List<CourseResponse> response = new ArrayList<>();
        page.forEach(x -> response.add(new CourseResponse(x.toAgregate())));

        return new PaginatedResponse(
                "ok", response, page.getTotalPages(),
                page.getNumberOfElements(), page.getTotalElements(),
                page.getSize(), page.getNumber());
    }
}
