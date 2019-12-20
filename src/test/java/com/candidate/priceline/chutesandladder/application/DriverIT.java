package com.candidate.priceline.chutesandladder.application;

import com.candidate.priceline.chutesandladder.facade.RequestFacade;
import com.candidate.priceline.chutesandladder.service.GameService;
import com.candidate.priceline.chutesandladder.service.Spinner;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DriverIT {

    @Spy
    private Spinner spinner = Spinner.getInstance();
    private String[][] playerNames;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        playerNames = new String[][]{{"CHARLIE", "ALAN"}, {"CHARLIE", "ALAN", "JAKE"}, {"CHARLIE", "ALAN", "JAKE", "ROSE"}};

    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * When Two Players are playing
     *  Test the startGame method of GameService
     *  Should return CHARLIE as Winner
     */
    @Test
    public void test_a_with_two_players_run() throws IOException {
        when(spinner.spin()).thenReturn(4).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(1).thenReturn(3).thenReturn(2).thenReturn(1).thenReturn(5).thenReturn(5).thenReturn(5).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(3).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(1).thenReturn(3).thenReturn(2).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(5).thenReturn(5).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(1).thenReturn(6).thenReturn(2);
        RequestFacade requestFacade = new RequestFacade();
        GameService gameService = new GameService();
        Driver driver = new Driver(playerNames[0]);
        String finalOutput = driver.run(requestFacade, gameService, spinner, playerNames[0]);
        assertThat("startGame test to verify Tom is winner in 2 player game", finalOutput, is("The winner is CHARLIE!"));
    }

    /**
     * When Three Players are playing
     *  Test the startGame method of GameService
     *  Should return CHARLIE as Winner
     */
    @Test
    public void test_b_with_three_players_run() throws IOException {
        when(spinner.spin()).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(2).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(3).thenReturn(2).thenReturn(2).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(3).thenReturn(4).thenReturn(4).thenReturn(6);
        RequestFacade requestFacade = new RequestFacade();
        GameService gameService = new GameService();
        Driver driver = new Driver(playerNames[1]);
        String finalOutput = driver.run(requestFacade, gameService, spinner, playerNames[1]);
        assertThat("startGame test to verify TOM is winner in 3 player game", finalOutput, is("The winner is CHARLIE!"));
    }

    /**
     * When Four Players are playing
     *  Test the startGame method of GameService
     *  Should return HARRY as Winner
     */
    @Test
    public void test_c_with_four_players_run() throws IOException {
        when(spinner.spin()).thenReturn(3).thenReturn(5).thenReturn(4).thenReturn(1).
                thenReturn(5).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(6).thenReturn(1).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(1).thenReturn(5).thenReturn(6).thenReturn(3).thenReturn(5).thenReturn(6).thenReturn(1).thenReturn(3).thenReturn(3).thenReturn(4).thenReturn(1).thenReturn(4).thenReturn(2).thenReturn(5).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(6).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(4).thenReturn(1).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(4).thenReturn(6).thenReturn(6).thenReturn(2).thenReturn(2).thenReturn(1).thenReturn(3).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(2).thenReturn(3).thenReturn(6).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(1).thenReturn(1).thenReturn(5).thenReturn(2).thenReturn(3).thenReturn(1).thenReturn(3).thenReturn(4).thenReturn(1).thenReturn(5).thenReturn(2).thenReturn(5).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(6).thenReturn(4).thenReturn(4).thenReturn(6).thenReturn(4).thenReturn(2).thenReturn(5).thenReturn(1);
        ;
        RequestFacade requestFacade = new RequestFacade();
        GameService gameService = new GameService();
        Driver driver = new Driver(playerNames[2]);
        String finalOutput = driver.run(requestFacade, gameService, spinner, playerNames[2]);
        assertThat("startGame test to verify HARRY is winner in 4 player game", finalOutput, is("The winner is JAKE!"));
    }

    /**
     * When Two Players are playing
     *  Test the startGame method of GameService is going through all chutes and ladders
     *  Should return ALAN as Winner
     */
    @Test
    public void test_d_with_2_players_all_chutesAndLadders_run() {
        when(spinner.spin()).thenReturn(5).thenReturn(3).thenReturn(1).thenReturn(4).thenReturn(6).thenReturn(2).thenReturn(6).thenReturn(3).thenReturn(1).thenReturn(5).thenReturn(4).thenReturn(3).thenReturn(3).thenReturn(5).thenReturn(1).thenReturn(5).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(4).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(6).thenReturn(4).thenReturn(3).thenReturn(6).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(2).thenReturn(2).thenReturn(6).thenReturn(3).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(6).thenReturn(4).thenReturn(6).thenReturn(5).thenReturn(5).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(3).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(6).thenReturn(1).thenReturn(6).thenReturn(1).
                thenReturn(2);
        RequestFacade requestFacade = new RequestFacade();
        GameService gameService = new GameService();
        Driver driver = new Driver(playerNames[0]);
        String finalOutput = driver.run(requestFacade, gameService, spinner, playerNames[0]);
        assertThat("startGame test to verify HARRY is winner in 4 player game", finalOutput, is("The winner is ALAN!"));
    }

}