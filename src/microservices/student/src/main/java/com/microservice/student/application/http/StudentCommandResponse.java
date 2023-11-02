package com.microservice.student.application.http;

import com.microservice.utils.core.http.IResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCommandResponse implements IResponse {

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
