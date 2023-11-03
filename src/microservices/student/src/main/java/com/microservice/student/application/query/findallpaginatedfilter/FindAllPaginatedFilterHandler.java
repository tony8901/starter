package com.microservice.student.application.query.findallpaginatedfilter;

import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.http.IRequestHandler;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class FindAllPaginatedFilterHandler
        implements IRequestHandler<FindAllPaginatedFilterQuery, PaginatedResponse> {

    private final IStudentService service;

    public FindAllPaginatedFilterHandler(IStudentService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindAllPaginatedFilterQuery request) {
        try{
            return service.findAllPaginatedFilter(
                    request.getPageable(), request.filter, request.name, request.email);
        } catch (Exception e){
            return new PaginatedResponse("Something is wrong!", e.getLocalizedMessage());
        }
    }
}
