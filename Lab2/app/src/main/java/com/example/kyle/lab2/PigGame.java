package com.example.kyle.lab2;

import java.util.Random;

public class PigGame {
    // initialize values
    private String player1Name;
    private String player2Name;
    private int p1TotalPoints = 0;
    private int p2TotalPoints = 0;
    private int playerTurn = 0;
    private int curPoints = 0;
    private int rollVal;
    private boolean isWinner = false;

    private Random rand = new Random();

    public int rollDie(){
        rollVal = (rand.nextInt(6)+1);
        curPoints += rollVal;
        return rollVal;
    }







}
