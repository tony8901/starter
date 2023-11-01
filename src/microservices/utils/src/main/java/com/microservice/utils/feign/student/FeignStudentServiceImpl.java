package com.microservice.utils.feign.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.microservice.utils.feign.student.client.IFeignStudentClient;
import com.microservice.utils.feign.student.service.IFeignStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeignStudentServiceImpl implements IFeignStudentService {

    private final IFeignStudentClient studentClient;

    private final ObjectReader jsonReader;

    public FeignStudentServiceImpl(IFeignStudentClient studentClient) {
        this.studentClient = studentClient;
        jsonReader = new ObjectMapper().reader();
    }

    @Override
    public ResponseEntity<String> getStudentsByCourseId(UUID courseId) {
        try{
//            JsonNode jsonResponse = jsonReader.readTree(studentClient.getStudentsByCourseId(courseId).getBody());
//            ObjectMapper objectMapper = new ObjectMapper();
//            FeignStudentByCourseIdResponse response =objectMapper.readValue(jsonResponse.get("data").toPrettyString(), FeignStudentByCourseIdResponse.class);
            return studentClient.getStudentsByCourseId(courseId);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
