package com.prod.ecommerce.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prod.ecommerce.util.AjaxResponse;




// Annotation: This controller handles exception.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResponse handleBindException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return AjaxResponse.error(
            new CustomException(
                CustomExceptionType.USER_INPUT_ERROR.getCode(),
                fieldError.getDefaultMessage()
            )
        );
    }
    
    // Argument Validation error can also trigger BindException.
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResponse handleBindException(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return AjaxResponse.error(
            new CustomException(
                CustomExceptionType.USER_INPUT_ERROR.getCode(),
                fieldError.getDefaultMessage()
            )
        );
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customException(CustomException e) {
        // System Error should be stored into log for maintenance.
        if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            // TODO: Save to log.
        }
        return AjaxResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse exception(Exception e) {
        // Undefined Error should be stored into log for maintenance.
        // TODO: Save to log.
        return AjaxResponse.error(new CustomException(
            CustomExceptionType.OTHER_ERROR));
    }
}
