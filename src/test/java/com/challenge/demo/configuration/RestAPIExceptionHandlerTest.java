package com.challenge.demo.configuration;

import com.challenge.demo.Constants;
import com.challenge.demo.service.entity.Error;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.challenge.demo.configuration.exceptionhandler.PropertyValidationConsumer.DELIMITER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class RestAPIExceptionHandlerTest {

    private RestAPIExceptionHandler handler;

    @Before
    public void setUp_() {
        handler = new RestAPIExceptionHandler();
    }

    @Test
    public void should_GenericErrorDescriptionCorrect() {
        Error error = handler.toError(new Exception());
        assertThat(Constants.GENERIC_ERROR, equalTo(error.getDescription()));
    }

    @Test
    public void should_DataIntegrityViolationErrorDescriptionCorrect() {
        Error error = handler.toError(new DataIntegrityViolationException(""));
        assertThat(Constants.VALUE_ALREADY_EXIST_ERROR, equalTo(error.getDescription()));
    }

    @Test
    public void should_PropertyValidationErrorDescriptionCorrect() {
        final String expected = "error!";

        ConstraintViolation<Object> mockViolation = Mockito.mock(ConstraintViolation.class);
        when(mockViolation.getMessage()).thenReturn(expected);
        Set<ConstraintViolation<Object>> violations = new HashSet<>(Arrays.asList(mockViolation));
        ConstraintViolationException ex = new ConstraintViolationException("", violations);

        Error error = handler.toError(ex);
        assertThat(expected, equalTo(error.getDescription()));
    }

    @Test
    public void should_MultiplePropertyValidationErrorDescriptionCorrect() {
        final String message = "error!";
        final String expected = message + DELIMITER + message;

        ConstraintViolation<Object> mockViolation1 = Mockito.mock(ConstraintViolation.class);
        when(mockViolation1.getMessage()).thenReturn(message);

        ConstraintViolation<Object> mockViolation2 = Mockito.mock(ConstraintViolation.class);
        when(mockViolation2.getMessage()).thenReturn(message);

        Set<ConstraintViolation<Object>> violations = new HashSet<>(Arrays.asList(mockViolation1, mockViolation2));
        ConstraintViolationException ex = new ConstraintViolationException("", violations);

        Error error = handler.toError(ex);
        assertThat(error.getDescription(), equalTo(expected));
    }

}