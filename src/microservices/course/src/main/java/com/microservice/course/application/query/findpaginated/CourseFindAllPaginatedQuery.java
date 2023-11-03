package com.microservice.course.application.query.findpaginated;

import com.microservice.utils.core.http.IRequest;
import org.springframework.data.domain.Pageable;

public record CourseFindAllPaginatedQuery(
        Pageable pageable,
        String name,
        String description,
        String state) implements IRequest {
}
