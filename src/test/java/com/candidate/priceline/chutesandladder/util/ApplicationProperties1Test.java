package com.candidate.priceline.chutesandladder.util;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationProperties1Test {
    static {
        System.setProperty("environment","dev");
    }
    ApplicationProperties applicationProperties;

    @Before
    public void setUp() throws Exception {
        applicationProperties = ApplicationProperties.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Given
     *          environment is provided as dev as System property
     *    and
     *          application.dev.properties file is present in resources directory
     *    and
     *          application.dev.properties has square.total = 101
     *  Then
     *      should return value as 101 as string.
     */
    @Ignore("Not able to simulate the System property change properly")
    @Test
    public void test_b_should_return_101_for_squares_total_getProperty() {
        assertEquals("Environment should match","dev", System.getProperty("environment"));
        assertThat("Application Property squares.total is 101",applicationProperties.getProperty("square.total"),is("101"));
    }

}