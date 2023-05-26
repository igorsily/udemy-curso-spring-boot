package com.br.igorsily.udemycursospringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //VIA QUERY PARAM (http://localhost:8080/api/v1/person?mediaType=xml)
//        configurer
//                .favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
//                .mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML)
//                .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON);


        //VIA HEADER PARAM (http://localhost:8080/api/v1/person)
        configurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML)
                .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON);
    }
}
