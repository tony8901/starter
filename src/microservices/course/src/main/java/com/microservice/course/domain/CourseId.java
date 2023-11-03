package com.microservice.course.domain;

import com.microservice.utils.core.objects.IdentifierValueObject;

import java.util.UUID;

public class CourseId extends IdentifierValueObject {
    public CourseId(UUID value) {
        super(value);
    }
}
