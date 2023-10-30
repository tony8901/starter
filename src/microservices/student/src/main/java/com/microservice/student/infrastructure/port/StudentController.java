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
import com.microservice.student.application.query.findall.FindAllStudentsResponse;
import com.microservice.student.application.query.findallpaginated.FindAllPaginatedQuery;
import com.microservice.student.application.query.findallpaginated.FindAllPaginatedHandler;
import com.microservice.utils.core.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final CreateStudentHandler createHandler;
    private final UpdateStudentHandler updateHandler;
    private final DeleteStudentHandler deleteHandler;
    private final FindAllStudentsHandler findAllHandler;

    private final FindAllPaginatedHandler findAllPaginatedHandler;

    public StudentController(CreateStudentHandler createHandler, UpdateStudentHandler updateHandler, DeleteStudentHandler deleteHandler, FindAllStudentsHandler findAllHandler, FindAllPaginatedHandler findAllPaginatedHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.findAllHandler = findAllHandler;
        this.findAllPaginatedHandler = findAllPaginatedHandler;
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
    public ResponseEntity<FindAllStudentsResponse> findAll(){
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
}
