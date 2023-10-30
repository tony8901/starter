package com.microservice.utils.core;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PaginatedResponse implements IRequestResponse{
    private String timestamp;

    private int status = 200;

    private String message;

    private String errors;

    private List<?> data = new ArrayList<>();

    private Integer totalPages;

    private Long totalElements;

    private Integer pageElements;

    private Integer pageLimit;

    private Integer pageNumber;

    public PaginatedResponse(String message, List<?> data, Integer totalPages,
                             Integer pageElements, Long totalElements, Integer pageLimit, Integer pageNumber){

        this.timestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss '['z']'")
                .format(ZonedDateTime.now(ZoneId.of("UTC")));
        this.message = message;
        this.errors = null;
        this.data = data;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageElements = pageElements;
        this.pageLimit = pageLimit;
        this.pageNumber = pageNumber;
    }

}
