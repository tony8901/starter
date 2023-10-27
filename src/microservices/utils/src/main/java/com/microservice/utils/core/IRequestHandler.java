package com.microservice.utils.core;

public interface IRequestHandler <R extends IRequest, T extends IRequestResponse>{
    T handle(R command);

}
