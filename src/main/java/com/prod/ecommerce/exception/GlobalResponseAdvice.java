package com.prod.ecommerce.exception;


import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.prod.ecommerce.util.AjaxResponse;


// Do last step handling before AjaxResponse is returned to FrontEnd.
// If not doing this, AjaxResponse will have error code but postman will
// have http status code 200.
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // Support all method parameters and all response classes.
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
        MethodParameter methodParameter,
        MediaType mediaType,
        Class aClass,
        ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
            // If the response is in json format,
            // This is for if we extend the application to support
            // html, we need to do separate handling for html response.
            if (mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)) {
                if (body instanceof AjaxResponse response) {
                    serverHttpResponse.setStatusCode(HttpStatusCode.valueOf(
                        response.getCode()
                        ));
                    return body;
                } else {
                    // 统一封装成功的AjaxResponse
                    serverHttpResponse.setStatusCode(HttpStatus.OK);
                    return AjaxResponse.success(body);
                }
            }
            
            return body;
    }
}
