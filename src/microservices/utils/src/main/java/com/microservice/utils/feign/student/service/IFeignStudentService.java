package com.microservice.utils.feign.student.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IFeignStudentService {

    ResponseEntity<String> getStudentsByCourseId(UUID courseId);
}
