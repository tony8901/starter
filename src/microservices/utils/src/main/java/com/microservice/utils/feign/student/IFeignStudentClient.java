package com.microservice.utils.feign.student;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "students", path = "/api/students")
public interface IFeignStudentClient {

}
