package com.challenge.demo.configuration;

import com.challenge.demo.Constants;
import com.challenge.demo.configuration.exceptionhandler.DataSourceViolationConsumer;
import com.challenge.demo.configuration.exceptionhandler.ExceptionConsumer;
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
            new DataSourceViolationConsumer());

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleExceptionsGlobally(
            Exception ex, WebRequest request) {

        BaseResponse response = new BaseResponse(toError(ex));

        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    Error toError(Exception ex) {
        String message = consumers.stream()
                .map(it -> it.tryToGetMessage(ex))
                .filter(it -> it != null)
                .findFirst()
                .orElse(Constants.GENERIC_ERROR);

        return Error.builder()
                .description(message)
                .build();
    }

}
