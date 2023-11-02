package com.microservice.utils.feign.student;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.microservice.utils.feign.student.client.IFeignStudentClient;
import com.microservice.utils.feign.student.http.FeignStudentResponse;
import com.microservice.utils.feign.student.service.IFeignStudentService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeignStudentServiceImpl implements IFeignStudentService {

    private final IFeignStudentClient studentClient;

    private final ObjectReader jsonReader;

    public FeignStudentServiceImpl(IFeignStudentClient studentClient) {
        this.studentClient = studentClient;
        jsonReader = new ObjectMapper().reader();
    }

    @Override
    public List<FeignStudentResponse> getStudentsByCourseId(UUID courseId) {
        try{
            JsonNode jsonResponse = jsonReader.readTree(studentClient.getStudentsByCourseId(courseId).getBody());
            ObjectMapper objectMapper = new ObjectMapper();
            List<FeignStudentResponse> studentResponses = Arrays.asList(objectMapper.readValue(jsonResponse.get("data").toString(), FeignStudentResponse[].class));
            return studentResponses;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
