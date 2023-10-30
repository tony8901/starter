package com.microservice.student.application.query.findallpaginated;

import com.microservice.utils.core.IRequest;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class FindAllPaginatedQuery implements IRequest {

    private final Pageable pageable;

    public FindAllPaginatedQuery(Pageable pageable) {
        this.pageable = pageable;
    }
}
