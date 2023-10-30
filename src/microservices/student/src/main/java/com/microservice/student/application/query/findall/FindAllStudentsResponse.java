package com.microservice.student.application.query.findall;

import com.microservice.student.application.http.StudentQueryResponse;
import com.microservice.utils.core.IRequestResponse;
import lombok.Getter;

import java.util.List;

public record FindAllStudentsResponse(List<StudentQueryResponse> data) implements IRequestResponse {

}
