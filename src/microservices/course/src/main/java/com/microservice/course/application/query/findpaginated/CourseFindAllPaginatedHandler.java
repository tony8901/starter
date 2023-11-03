package com.microservice.course.application.query.findpaginated;

import com.microservice.course.domain.CourseDescription;
import com.microservice.course.domain.CourseName;
import com.microservice.course.domain.CourseState;
import com.microservice.course.domain.service.ICourseService;
import com.microservice.utils.core.http.IRequestHandler;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.stereotype.Component;

@Component
public class CourseFindAllPaginatedHandler
        implements IRequestHandler<CourseFindAllPaginatedQuery, PaginatedResponse> {

    private final ICourseService service;

    public CourseFindAllPaginatedHandler(ICourseService service) {
        this.service = service;
    }

    @Override
    public PaginatedResponse handle(CourseFindAllPaginatedQuery request) {
        try {
            CourseName name = !request.name().isBlank() ? new CourseName(request.name()) : null;
            CourseDescription description = !request.description().isBlank() ? new CourseDescription(request.description()) : null;
            CourseState state = validateState(request.state());
            return service.findAllPaginatedFilter(request.pageable(), name, description, state);
        } catch (Exception e){
            throw new RuntimeException(e.fillInStackTrace());
        }
    }

    private CourseState validateState(String state){
        if(state.isBlank()){
            return CourseState.ACTIVE;
        }

        String toUpperCase = state.toUpperCase();
        if (toUpperCase.equals(CourseState.INACTIVE.name())){
            return CourseState.INACTIVE;
        }
        return CourseState.ACTIVE;
    }
}
