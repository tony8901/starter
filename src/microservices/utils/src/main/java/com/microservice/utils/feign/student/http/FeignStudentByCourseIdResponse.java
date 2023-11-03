package com.microservice.utils.feign.student.http;

import java.util.List;

public record FeignStudentByCourseIdResponse(List<FeignStudentResponse> data) {

}
