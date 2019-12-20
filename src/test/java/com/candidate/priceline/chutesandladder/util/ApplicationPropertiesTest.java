package com.candidate.priceline.chutesandladder.util;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationPropertiesTest {

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
     *          no environment provided
     *    and
     *          application.properties file is present in resources directory
     *    and
     *          application.properties has square.total = 100
     * Then
     *      should return value as 100 as string.
     */
    @Test
    public void test_a_should_return_100_for_squares_total_getProperty() {
        assertThat("Application Property squares.total is 100",applicationProperties.getProperty("square.total"),is("100"));
    }

    /**
     * Given
     *          environment is provided as "dev1" as System property
     *    and
     *          application.dev1.properties file is NOT present in resources directory
     * Then
     *      should return value as 100 as string from application.properties
     */
    @Test
    public void test_c_should_return_100_for_squares_total_getProperty() {
        assertThat("Application Property squares.total is 100",applicationProperties.getProperty("square.total"),is("100"));
    }

    /**
     * Given
     *          no environment provided
     *    and
     *          application.properties file is present in resources directory
     *    and
     *          application.properties has square.total = 100 and location.chutesandladder = chutes_ladders.json
     * Then
     *    should return an array of properties with values as below
     *    square.total = "100" and location.chutesandladder = "chutes_ladders.json"
     */
    @Test
    public void test_d_should_return_array_properties_getproperties() {
        assertThat("Returns the instance of Properties.class",applicationProperties.getProperties(),instanceOf(Properties.class));
        assertThat("Application Property squares.total is 100",applicationProperties.getProperties().getProperty("square.total"),is("100"));
        assertThat("Application Property location.chutesandladder is chutes_ladders.json",applicationProperties.getProperties().getProperty("location.chutesandladder"),is("chutes_ladders.json"));
    }

    /**
     * Given
     *          environment is provided as "dev1" as System property
     *    and
     *          application.dev1.properties file is present in resources directory
     *    and
     *          application.properties has square.total = 100 and location.chutesandladder = chutes_ladders.json
     * Then
     *    should return an array of properties with values as below
     *    square.total = "100" and location.chutesandladder = "chutes_ladders.json"
     */
    @Test
    public void test_e_should_return_array_properties_getproperties() {
        assertThat("Returns the instance of Properties.class",applicationProperties.getProperties(),instanceOf(Properties.class));
        assertThat("Application Property squares.total is 100",applicationProperties.getProperties().getProperty("square.total"),is("100"));
        assertThat("Application Property location.chutesandladder is chutes_ladders.json",applicationProperties.getProperties().getProperty("location.chutesandladder"),is("chutes_ladders.json"));
    }


}