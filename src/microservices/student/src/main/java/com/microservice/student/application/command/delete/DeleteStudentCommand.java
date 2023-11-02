package com.microservice.student.application.command.delete;

import com.microservice.utils.core.http.IRequest;
import java.util.UUID;

public record DeleteStudentCommand(UUID id) implements IRequest {

}
