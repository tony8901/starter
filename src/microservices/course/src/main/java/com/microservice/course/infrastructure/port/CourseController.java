package com.microservice.course.infrastructure.port;

import com.microservice.course.application.command.create.CourseCreateCommand;
import com.microservice.course.application.command.create.CourseCreateHandler;
import com.microservice.course.application.command.create.CourseCreateRequest;
import com.microservice.course.application.command.delete.CourseDeleteCommand;
import com.microservice.course.application.command.delete.CourseDeleteHandle;
import com.microservice.course.application.command.update.CourseUpdateCommand;
import com.microservice.course.application.command.update.CourseUpdateHandler;
import com.microservice.course.application.command.update.CourseUpdateRequest;
import com.microservice.course.application.http.CourseAllQueryResponse;
import com.microservice.course.application.http.CourseCommandResponse;
import com.microservice.course.application.http.StudentsAllResponse;
import com.microservice.course.application.query.findall.CourseFindAllHandler;
import com.microservice.course.application.query.findall.CourseFindAllQuery;
import com.microservice.course.application.query.findpaginated.CourseFindAllPaginatedHandler;
import com.microservice.course.application.query.findpaginated.CourseFindAllPaginatedQuery;
import com.microservice.course.application.query.findstudents.CourseFindStudentsHandler;
import com.microservice.course.application.query.findstudents.CourseFindStudentsQuery;
import com.microservice.utils.core.http.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("courses/api/courses")
public class CourseController {

    private final CourseCreateHandler createHandler;

    private final CourseUpdateHandler updateHandler;

    private final CourseDeleteHandle deleteHandle;

    private final CourseFindAllHandler findAllHandler;

    private final CourseFindAllPaginatedHandler paginatedHandler;

    private final CourseFindStudentsHandler studentsHandler;

    public CourseController(CourseCreateHandler createHandler, CourseUpdateHandler updateHandler, CourseDeleteHandle deleteHandle, CourseFindAllHandler findAllHandler, CourseFindAllPaginatedHandler paginatedHandler, CourseFindStudentsHandler studentsHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandle = deleteHandle;
        this.findAllHandler = findAllHandler;
        this.paginatedHandler = paginatedHandler;
        this.studentsHandler = studentsHandler;
    }

    @PostMapping
    public ResponseEntity<CourseCommandResponse> save(@RequestBody CourseCreateRequest request){
        CourseCreateCommand command = CourseCreateCommand.fromRequest(request);
        CourseCommandResponse response = createHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CourseCommandResponse> update(@RequestBody CourseUpdateRequest request){
        CourseUpdateCommand command = CourseUpdateCommand.fromRequest(request);
        CourseCommandResponse response = updateHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseCommandResponse> delete(@PathVariable UUID id){
        CourseDeleteCommand command = new CourseDeleteCommand(id);
        CourseCommandResponse response = deleteHandle.handle(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<CourseAllQueryResponse> findAll(){
        CourseFindAllQuery query = new CourseFindAllQuery();
        CourseAllQueryResponse response = findAllHandler.handle(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse> findAllPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String description,
            @RequestParam(defaultValue = "active") String state){

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        CourseFindAllPaginatedQuery query = new CourseFindAllPaginatedQuery(pageable, name, description, state);
        PaginatedResponse response = paginatedHandler.handle(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/students/{courseId}")
    public ResponseEntity<StudentsAllResponse> findStudents(@PathVariable UUID courseId){
        CourseFindStudentsQuery query = new CourseFindStudentsQuery(courseId);
        StudentsAllResponse response = studentsHandler.handle(query);

        return ResponseEntity.ok(response);
    }

}
