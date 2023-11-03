package com.microservice.student.infrastructure.port;

import com.microservice.student.application.command.create.CreateStudentCommand;
import com.microservice.student.application.command.create.CreateStudentHandler;
import com.microservice.student.application.command.create.CreateStudentRequest;
import com.microservice.student.application.command.delete.DeleteStudentCommand;
import com.microservice.student.application.command.delete.DeleteStudentHandler;
import com.microservice.student.application.command.update.UpdateStudentCommand;
import com.microservice.student.application.command.update.UpdateStudentHandler;
import com.microservice.student.application.command.update.UpdateStudentRequest;
import com.microservice.student.application.http.StudentCommandResponse;
import com.microservice.student.application.query.findall.FindAllStudentsHandler;
import com.microservice.student.application.http.AllStudentsResponse;
import com.microservice.student.application.query.findallpaginated.FindAllPaginatedQuery;
import com.microservice.student.application.query.findallpaginated.FindAllPaginatedHandler;
import com.microservice.student.application.query.findallpaginatedfilter.FindAllPaginatedFilterHandler;
import com.microservice.student.application.query.findallpaginatedfilter.FindAllPaginatedFilterQuery;
import com.microservice.student.application.query.getbycourseid.FindByCourseIdHandler;
import com.microservice.student.application.query.getbycourseid.FindByCourseIdQuery;
import com.microservice.utils.core.http.PaginatedResponse;
import com.microservice.utils.feign.student.service.IFeignStudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students/api/students")
public class StudentController {

    private final CreateStudentHandler createHandler;
    private final UpdateStudentHandler updateHandler;
    private final DeleteStudentHandler deleteHandler;
    private final FindAllStudentsHandler findAllHandler;
    private final FindAllPaginatedHandler findAllPaginatedHandler;
    private final FindAllPaginatedFilterHandler findAllPaginatedFilterHandler;
    private final FindByCourseIdHandler courseIdHandler;
    private final IFeignStudentService studentService;

    public StudentController(CreateStudentHandler createHandler, UpdateStudentHandler updateHandler, DeleteStudentHandler deleteHandler, FindAllStudentsHandler findAllHandler, FindAllPaginatedHandler findAllPaginatedHandler, FindAllPaginatedFilterHandler findAllPaginatedFilterHandler, FindByCourseIdHandler courseIdHandler, IFeignStudentService studentService) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.findAllHandler = findAllHandler;
        this.findAllPaginatedHandler = findAllPaginatedHandler;
        this.findAllPaginatedFilterHandler = findAllPaginatedFilterHandler;
        this.courseIdHandler = courseIdHandler;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentCommandResponse> create(@RequestBody CreateStudentRequest request){
        CreateStudentCommand command = CreateStudentCommand.fromRequest(request);
        StudentCommandResponse response = createHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<StudentCommandResponse> update(@RequestBody UpdateStudentRequest request){
        UpdateStudentCommand command = UpdateStudentCommand.fromRequest(request);
        StudentCommandResponse response = updateHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentCommandResponse> delete(@PathVariable UUID id){
        DeleteStudentCommand command = new DeleteStudentCommand(id);
        StudentCommandResponse response = deleteHandler.handle(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<AllStudentsResponse> findAll(){
        return ResponseEntity.ok(findAllHandler.handle());
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse> finAllPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        FindAllPaginatedQuery query = new FindAllPaginatedQuery(pageable);
        PaginatedResponse response = findAllPaginatedHandler.handle(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginatedResponse> findAllPaginatedFilter(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email){
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        FindAllPaginatedFilterQuery query = new FindAllPaginatedFilterQuery(
                pageable, filter, name, email);
        PaginatedResponse response = findAllPaginatedFilterHandler.handle(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<AllStudentsResponse> findByCourseId(@PathVariable UUID courseId){
        FindByCourseIdQuery query = new FindByCourseIdQuery(courseId);
        AllStudentsResponse response = courseIdHandler.handle(query);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/prueba/{courseId}")
    public ResponseEntity<?> prueba(@PathVariable UUID courseId){
        return ResponseEntity.ok(studentService.getStudentsByCourseId(courseId));
    }
}
