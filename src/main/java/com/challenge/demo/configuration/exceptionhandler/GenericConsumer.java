package com.challenge.demo.configuration.exceptionhandler;

import static com.challenge.demo.Constants.GENERIC_ERROR;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class GenericConsumer implements ExceptionConsumer {

    @Override
    public boolean handle(Exception ex, StringBuilder builder) {
        String exceptionMessage = ex.getMessage() == null ? GENERIC_ERROR :  ex.getMessage();
        builder.append(exceptionMessage);

        return true;
    }
}
