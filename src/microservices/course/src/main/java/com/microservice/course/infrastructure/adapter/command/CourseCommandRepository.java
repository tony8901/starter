package com.microservice.course.infrastructure.adapter.command;

import com.microservice.course.domain.Course;
import com.microservice.course.domain.CourseState;
import com.microservice.course.domain.repository.command.ICourseCommandRepository;
import com.microservice.course.infrastructure.entities.hibernate.CourseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandRepository implements ICourseCommandRepository {

    private final ICourseWritteDataJpaRepository repository;

    public CourseCommandRepository(ICourseWritteDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Course course) {
        repository.save(new CourseDto(course));
    }

    @Override
    public void update(Course course) {
        repository.save(new CourseDto(course));
    }

    @Override
    public void delete(Course course) {
        Course toDelete = new Course(
                course.id(),
                course.name(),
                course.description(),
                CourseState.INACTIVE
        );
        repository.save(new CourseDto(toDelete));
    }
}
