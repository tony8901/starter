package com.microservice.course.application.http;

import com.microservice.utils.core.http.IResponse;

import java.util.List;

public record CourseAllQueryResponse(List<CourseResponse> data) implements IResponse {
}
