package com.challenge.demo.configuration.exceptionhandler;

import com.challenge.demo.Constants;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public final class DataSourceViolationConsumer implements ExceptionConsumer {

    @Override
    public String tryToGetMessage(Exception ex) {
        if (ex instanceof DataIntegrityViolationException) {
            return Constants.VALUE_ALREADY_EXIST_ERROR;
        }

        return null;
    }

}
