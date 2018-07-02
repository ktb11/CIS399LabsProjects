package com.example.kyle.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener, EditText.OnEditorActionListener {

    // define variables for widgets
    private Button rollDie;
    private Button endTurn;
    private TextView turnPoints;
    private ImageView DieImage;
    private TextView player1Score;
    private TextView player2Score;
    private TextView whosTurn;
    private TextView winner;
    private EditText p1Name;
    private EditText p2Name;
    private Button newGame;

    // initializing values, need to move some to PigGame class
    private int p1TotalPoints = 0;
    private int p2TotalPoints = 0;
    private int playerTurn = 0;
    private int curPoints = 0;
    private int rollVal;
    private boolean isWinner = false;
    private String player1Name;
    private String player2Name;


    private Random rand = new Random();

    PigGame game = new PigGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referring widgets
        rollDie = (Button)findViewById(R.id.rollBtn);
        endTurn = (Button)findViewById(R.id.endTurnBtn);
        turnPoints = (TextView)findViewById(R.id.turnPointsId);
        DieImage = (ImageView)findViewById(R.id.dieImgId);
        player1Score = (TextView)findViewById(R.id.player1ScoreId);
        player2Score = (TextView)findViewById(R.id.player2ScoreId);
        whosTurn = (TextView)findViewById(R.id.curPlayerNameID);
        winner = (TextView)findViewById(R.id.winnerTextView);
        p1Name = (EditText)findViewById(R.id.player1NameId);
        p2Name = (EditText)findViewById(R.id.player2NameId);
        newGame = (Button)findViewById(R.id.newGameBtn);



        // set listeners
        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);
        newGame.setOnClickListener(this);
        p1Name.setOnEditorActionListener(this);
        p2Name.setOnEditorActionListener(this);
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

            case R.id.newGameBtn:
                newGame();
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
            whosTurn.setText(player1Name);
        }
        else {
            whosTurn.setText(player2Name);
        }
    }

    private void determineWinner(){
        if(p1TotalPoints >= 11 && p2TotalPoints < 11){
            winner.setText(player1Name);
            isWinner = true;

        }
        if(p2TotalPoints >= 11 && p1TotalPoints < 11){
            winner.setText(player2Name);
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

    private void newGame(){
        player1Name = "";
        player2Name = "";
        playerTurn = 0;
        p1TotalPoints = 0;
        p2TotalPoints = 0;
        curPoints = 0;
        player1Score.setText(Integer.toString(p1TotalPoints));
        player2Score.setText(Integer.toString(p2TotalPoints));
        winner.setText("waiting");
        p1Name.setText("");
        p2Name.setText("");

    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            switch (view.getId()) {
                case R.id.player1NameId:
                    player1Name = p1Name.getText().toString();
                    break;
                case R.id.player2NameId:
                    player2Name = p2Name.getText().toString();
                    break;
            }
        }
        return false;
    }
}
