package com.example.kyle.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // define variables for widgets
    private Button rollDie;
    private Button endTurn;
    private TextView turnPoints;
    private ImageView DieImage;
    private TextView player1Score;
    private TextView player2Score;
    private TextView whosTurn;
    private TextView winner;

    private int p1TotalPoints = 0;
    private int p2TotalPoints = 0;
    private int playerTurn = 0;
    private int curPoints = 0;
    private int rollVal;
    private boolean isWinner = false;


    private Random rand = new Random();

    PigGame game = new PigGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDie = (Button)findViewById(R.id.rollBtn);
        endTurn = (Button)findViewById(R.id.endTurnBtn);
        turnPoints = (TextView)findViewById(R.id.turnPointsId);
        DieImage = (ImageView)findViewById(R.id.dieImgId);
        player1Score = (TextView)findViewById(R.id.player1ScoreId);
        player2Score = (TextView)findViewById(R.id.player2ScoreId);
        whosTurn = (TextView)findViewById(R.id.curPlayerNameID);
        winner = (TextView)findViewById(R.id.winnerTextView);

        // set listeners
        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = 0;

        switch (v.getId()) {
            case R.id.rollBtn:

                rollVal = (rand.nextInt(6)+1);
                displayImage(rollVal);
                if (rollVal == 1){
                    curPoints = 0;
                    playerTurn += 1;
                    break;
                }
                curPoints += rollVal;
                break;


            case R.id.endTurnBtn:
                endTurn();
                break;

        }

        determineWinner();

    }

    // display Die after roll button
    private void displayImage(int rollValue){
        int id = 0;

        switch(rollValue){
            case 1:
                id = R.drawable.die1;
                break;
            case 2:
                id = R.drawable.die2;
                break;
            case 3:
                id = R.drawable.die3;
                break;
            case 4:
                id = R.drawable.die4;
                break;
            case 5:
                id = R.drawable.die5;
                break;
            case 6:
                id = R.drawable.die6;
                break;

        }
        DieImage.setImageResource(id);
    }

    private void getPlayerTurn(int whosTurnValue){

        if (whosTurnValue == 0){
            whosTurn.setText("player1");
        }
        else {
            whosTurn.setText("player2");
        }
    }

    private void determineWinner(){
        if(p1TotalPoints >= 11 && p2TotalPoints < 11){
            winner.setText("player1");
            isWinner = true;

        }
        if(p2TotalPoints >= 11 && p1TotalPoints < 11){
            winner.setText("player2");
            isWinner = true;
        }
        turnPoints.setText(Integer.toString(curPoints));
        if (playerTurn > 1)
            playerTurn = 0;
        getPlayerTurn(playerTurn);

    }

    private void endTurn(){
        if (playerTurn == 0){
            p1TotalPoints += curPoints;
            player1Score.setText(Integer.toString(p1TotalPoints));
        }
        else {
            p2TotalPoints += curPoints;
            player2Score.setText(Integer.toString(p2TotalPoints));
        }

        curPoints = 0;
        playerTurn += 1;
    }
}
