package com.microservice.student.application.http;

import com.microservice.utils.core.IRequestResponse;

import java.util.List;

public record AllStudentsResponse(List<StudentQueryResponse> data) implements IRequestResponse {

}
