package com.microservice.student.application.query.getbycourseid;

import com.microservice.utils.core.IRequest;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindByCourseIdQuery implements IRequest {
    private final UUID courseId;

    public FindByCourseIdQuery(UUID courseId) {
        this.courseId = courseId;
    }
}
