package com.microservice.utils.feign.student.service;

import com.microservice.utils.feign.student.http.FeignStudentResponse;

import java.util.List;
import java.util.UUID;

public interface IFeignStudentService {

    List<FeignStudentResponse> getStudentsByCourseId(UUID courseId);
}
