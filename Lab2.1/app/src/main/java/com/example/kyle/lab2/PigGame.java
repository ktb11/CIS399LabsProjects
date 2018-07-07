package com.example.kyle.lab2;

import android.widget.Toast;

import java.util.Random;

public class PigGame {
    // initialize values
    public String player1Name;
    public String player2Name;
    public int p1TotalPoints = 0;
    public int p2TotalPoints = 0;
    public int playerTurn = 0;
    public int curPoints = 0;
    public int rollVal;
    public boolean isWinner = false;
    public String winner;
    public int whosTurnVal;

    private Random rand = new Random();

    public int rollDie(){
        rollVal = (rand.nextInt(6)+1);
        if (rollVal == 1){
            endTurn();
        }
        return rollVal;
    }

    public void endTurn(){
        if (playerTurn == 0){
            p1TotalPoints += curPoints;
        }
        else {
            p2TotalPoints += curPoints;
        }
        curPoints = 0;
        playerTurn += 1;
        playerTurn %= 2;
    }

    public void newGame(){
        player1Name = "";
        player2Name = "";
        playerTurn = 0;
        p1TotalPoints = 0;
        p2TotalPoints = 0;
        curPoints = 0;

    }

    public void determineWinner(){
        if(p1TotalPoints >= 100 && p2TotalPoints < 100){
            winner = player1Name;
        }
        if(p2TotalPoints >= 100 && p1TotalPoints < 100){
            winner = player2Name;
        }
        if (playerTurn > 1)
            playerTurn = 0;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }







}
