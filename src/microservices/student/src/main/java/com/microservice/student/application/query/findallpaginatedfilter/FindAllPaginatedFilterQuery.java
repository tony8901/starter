package com.microservice.student.application.query.findallpaginatedfilter;

import com.microservice.utils.core.IRequest;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class FindAllPaginatedFilterQuery implements IRequest {
    Pageable pageable;

    String filter;

    String name;

    String email;

    public FindAllPaginatedFilterQuery(Pageable pageable, String filter, String name, String email) {
        this.pageable = pageable;
        this.filter = filter;
        this.name = name;
        this.email = email;
    }
}
