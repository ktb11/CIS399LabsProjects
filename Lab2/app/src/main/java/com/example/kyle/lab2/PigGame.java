package com.example.kyle.lab2;

import java.util.Random;

public class PigGame {
    // initialize values
    String player1 = "";
    String player2 = "";
    int player1Score = 0;
    int player2Score = 0;
    int turnPoints = 0;
    int rollVal;

    private Random rand = new Random();

    public int rollDie(){
        rollVal = (rand.nextInt(6)+1);
        turnPoints += rollVal;
        return rollVal;
    }





}
