package com.candidate.priceline.chutesandladder.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpinnerTest {


    /**
     * Test to verify Sprinner.getInstance returns the instance of type of Spinner
     */
    @Test
    public void test_a_is_spinner_type_instance_getInstance() {
        Spinner spinner = Spinner.getInstance();
        assertThat("Is a instance of Spinner class.", spinner, instanceOf(Spinner.class));
    }

    /**
     * Given
     *      Spinner Singeleton Instance available
     * then
     *      verify Spiner.getInstance always returns singleton instance that is the same instance
     */
    @Test
    public void test_b_is_singleton_instance_of_spinner_by_getInstance() {
        Spinner spinner1 = Spinner.getInstance();

        for (int i = 0; i < 1000000; i++)
            assertThat("Is same instance associated with spinner1, spinner2", Spinner.getInstance(), sameInstance(spinner1));
    }

    /**
     * Given
     *      Spinner Singeleton Instance available
     * then
     *      Spinner.spin method returns the value of type of int
     */
    @Test
    public void test_c_spin_returns_integer_spin() {
        Spinner spinner = Spinner.getInstance();
        assertThat("Spin() method returns the value of type Integer", spinner.spin(), instanceOf(Integer.class));
        assertThat("Spin() method returns the value NOT of type Integer", spinner.spin(), not(instanceOf(String.class)));
    }

    /**
     * Given
     *      Spinner Singeleton Instance available
     * then
     *      Spinner.spin method should always return the value less than or equal to 6
     */
    @Test
    public void test_d_spin_returns_integer_less_than_or_equal_to_6() {
        Spinner spinner = Spinner.getInstance();
        for (int i = 0; i < 1000000; i++)
            assertTrue("Spin() method returns the value less than or equal to 6", spinner.spin() <= 6);
    }

    /**
     * Given
     *      Spinner Singeleton Instance available
     * then
     *      Spinner.spin method should always return the value greater than or equal to 1
     */
    @Test
    public void test_e_spin_returns_integer_greater_than_or_equal_to_1() {
        Spinner spinner = Spinner.getInstance();
        for (int i = 0; i < 1000000; i++)
            assertTrue("Spin() method returns the value greater than or equal to 1", spinner.spin() >= 1);
    }
}