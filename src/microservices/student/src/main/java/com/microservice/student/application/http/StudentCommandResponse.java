package com.microservice.student.application.http;

import com.microservice.utils.core.IRequestResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCommandResponse implements IRequestResponse {

    private String message;
    private String id;
    private boolean error;

    public StudentCommandResponse() {
    }

    public StudentCommandResponse(String id, String message, boolean error) {
        this.message = message;
        this.id = id;
        this.error = error;
    }

}
