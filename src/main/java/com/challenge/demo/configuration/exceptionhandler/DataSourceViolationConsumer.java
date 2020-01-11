package com.challenge.demo.configuration.exceptionhandler;

import com.challenge.demo.Constants;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class DataSourceViolationConsumer implements ExceptionConsumer {

    @Override
    public boolean handle(Exception ex, StringBuilder builder) {
        if (ex instanceof DataIntegrityViolationException) {
            builder.append(Constants.VALUE_ALREADY_EXIST_ERROR);
            return true;
        }
        return false;
    }
}
