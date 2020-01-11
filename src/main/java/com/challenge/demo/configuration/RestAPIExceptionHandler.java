package com.challenge.demo.configuration;

import com.challenge.demo.configuration.exceptionhandler.DataSourceViolationConsumer;
import com.challenge.demo.configuration.exceptionhandler.ExceptionConsumer;
import com.challenge.demo.configuration.exceptionhandler.GenericConsumer;
import com.challenge.demo.configuration.exceptionhandler.PropertyValidationConsumer;
import com.challenge.demo.service.entity.BaseResponse;
import com.challenge.demo.service.entity.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

    private static final List<ExceptionConsumer> consumers = Arrays.asList(
            new PropertyValidationConsumer(),
            new DataSourceViolationConsumer(),
            new GenericConsumer());

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleExceptionsGlobally(
            Exception ex, WebRequest request) {

        BaseResponse response = new BaseResponse(false, toError(ex));

        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    Error toError(Exception ex) {
        StringBuilder messageBuilder = new StringBuilder();

        consumers.stream()
                .filter(it -> it.handle(ex, messageBuilder))
                .findFirst();

        String message = messageBuilder.toString();

        return Error.builder()
                .description(message)
                .build();
    }

}
