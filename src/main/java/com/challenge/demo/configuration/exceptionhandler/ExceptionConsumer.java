package com.challenge.demo.configuration.exceptionhandler;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public interface ExceptionConsumer {

    boolean handle(Exception ex, StringBuilder builder);

}
