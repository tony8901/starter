package com.microservice.course.infrastructure.service;

import com.microservice.course.domain.*;
import com.microservice.course.domain.repository.command.ICourseCommandRepository;
import com.microservice.course.domain.repository.query.ICourseQueryRepository;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDomainService implements ICourseService {

    private final ICourseCommandRepository commandRepository;

    private final ICourseQueryRepository queryRepository;

    public CourseDomainService(ICourseCommandRepository commandRepository, ICourseQueryRepository queryRepository) {
        this.commandRepository = commandRepository;
        this.queryRepository = queryRepository;
    }

    @Override
    public void create(Course course) {
        commandRepository.create(course);
    }

    @Override
    public void update(Course course) {
        commandRepository.update(course);
    }

    @Override
    public void delete(Course course) {
        commandRepository.delete(course);
    }

    @Override
    public List<Course> findAll() {
        return queryRepository.findAll();
    }

    @Override
    public Course findById(CourseId id) {
        return queryRepository.findById(id);
    }

    @Override
    public PaginatedResponse findAllPaginatedFilter(Pageable pageable, CourseName name, CourseDescription description, CourseState state) {
        return queryRepository.findAllPaginatedFilter(pageable, name, description, state);
    }
}
