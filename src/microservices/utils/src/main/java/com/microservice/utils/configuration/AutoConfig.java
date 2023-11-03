package com.microservice.utils.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.microservice.utils")
@EnableFeignClients(basePackages = "com.microservice.utils.feign")
public class AutoConfig {
}
