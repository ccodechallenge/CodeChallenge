package com.challenge.demo.service.specification;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by nurisezgin on 13.01.2020.
 */
public class AutoLoaderTest {

    @Test
    public void should_SingleStringCorrect() {
        final int expected = 1;

        ToBeLoaded object = ToBeLoaded.builder()
                .name("John")
                .build();

        Map<String, String> attributes = new HashMap<>();

        AutoLoader.with(object)
                .load(it -> attributes.put(it.getFirst(), it.getSecond()));

        int actual = attributes.size();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_MultipleStringCorrect() {
        final int expected = 2;

        ToBeLoaded object = ToBeLoaded.builder()
                .name("John")
                .lastName("Nesh")
                .build();

        Map<String, String> attributes = new HashMap<>();

        AutoLoader.with(object)
                .load(it -> attributes.put(it.getFirst(), it.getSecond()));

        int actual = attributes.size();

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void should_NoFilledOutFieldCorrect() {
        final int expected = 0;

        ToBeLoaded object = ToBeLoaded.builder()
                .build();

        Map<String, String> attributes = new HashMap<>();

        AutoLoader.with(object)
                .load(it -> attributes.put(it.getFirst(), it.getSecond()));

        int actual = attributes.size();

        assertThat(actual, equalTo(expected));
    }

    @Builder
    @Data
    static class ToBeLoaded {

        private String name;
        private String lastName;

    }

}