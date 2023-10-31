package com.microservice.student.application.query.findallpaginated;

import com.microservice.student.domain.service.IStudentService;
import com.microservice.utils.core.IRequestHandler;
import com.microservice.utils.core.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class FindAllPaginatedHandler
        implements IRequestHandler<FindAllPaginatedQuery, PaginatedResponse> {

    private final IStudentService service;

    public FindAllPaginatedHandler(IStudentService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(FindAllPaginatedQuery request) {
        try{
            return service.findAllPaginated(request.getPageable());
        } catch (Exception e){
            return new PaginatedResponse("Something is wrong!", e.getLocalizedMessage());
        }
    }
}
