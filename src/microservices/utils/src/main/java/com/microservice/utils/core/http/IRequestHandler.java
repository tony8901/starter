package com.microservice.utils.core.http;

public interface IRequestHandler <R extends IRequest, T extends IResponse>{
    T handle(R request);

}
