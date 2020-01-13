package com.challenge.demo.service.specification;

import org.springframework.data.util.Pair;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by nurisezgin on 13.01.2020.
 */
final class AutoLoader {

    private final Object value;
    private static final Predicate<Pair<String, String>> notEmpty = v -> v.getSecond() != null
            && !v.getSecond().toString().isEmpty();

    private AutoLoader(Object value) {
        this.value = value;
    }

    static AutoLoader with(Object value) {
        return new AutoLoader(value);
    }

    void load(Consumer<Pair<String, String>> consumer) {
        Field[] fields = value.getClass().getDeclaredFields();

        Stream.of(fields)
                .map(this::toAccessibleField)
                .map(this::toNameValuePair)
                .filter(notEmpty)
                .forEach(consumer);
    }

    private Field toAccessibleField(Field field) {
        field.setAccessible(true);
        return field;
    }

    private Pair<String, String> toNameValuePair(Field field) {
        String name = field.getName();
        Object value = null;

        try {
            value = field.get(this.value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return Pair.of(name, normalizeValue(value));
    }

    private String normalizeValue(Object value) {
        if (value == null) {
            value = "";
        }

        return value.toString();
    }
}
