package com.microservice.utils.feign.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "students", path = "/students/api")
public interface IFeignStudentClient {

    @RequestMapping(value = "/course/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getStudentsByCourseId(@PathVariable UUID courseId);
}
