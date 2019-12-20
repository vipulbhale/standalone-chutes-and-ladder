package com.candidate.priceline.chutesandladder.service;

import java.util.Random;

public class Spinner {

    private static Spinner spinner;

    private Spinner() {
    }

    /**
     *
     * @return Spinner singleton instance.
     */
    public static Spinner getInstance(){
        if(spinner == null)
            spinner = new Spinner();
        return spinner;
    }

    /**
     *
     * @return integer greater than or equal to 1 and less than or equal to 6.
     * Assumption for values 1-6 based on the diagram/pdf shared
     */
    public int spin() {
        return new Random().nextInt(6) + 1;
    }
}
