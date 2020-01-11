package com.challenge.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@RunWith(Parameterized.class)
public class OSValidatorTest {

    private String os;
    private boolean expected;

    public OSValidatorTest(String os, boolean expected) {
        this.os = os;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Android", true },
                { "iOS", true },
                { "abc", false },
                { "", false },
                { null, false }
        });
    }

    @Test
    public void should_Correct() {
        boolean actual = new OSValidator().isValid(os, null);

        assertThat(expected, equalTo(actual));
    }

}