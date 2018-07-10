package com.example.kyle.lab4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Random;

public class SecondFragment extends Fragment implements Button.OnClickListener {

    // widget declarations
    TextView p1Name;
    TextView p2Name;
    TextView p1ScoreLabel;
    TextView p2ScoreLabel;
    Button rollBtn;
    Button endTurnBtn;
    TextView currentScoreLabel;
    TextView whosTurn;
    ImageView DieImage;
    TextView winner;


    // local variables
    private int currentScore = 0;
    private int p1Score = 0;
    private int p2Score = 0;
    private int rollVal;
    String player1Name;
    String player2Name;
    private int playerTurn = 0;
    private int winningScore = 100;

    private Random rand = new Random();


    // Shared Information
    private SharedPreferences savedValues;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());
        p1Name = (TextView) view.findViewById(R.id.displayPlayer1Name);
        p1Name.setText(savedValues.getString("p1Name", "player1" ));
        player1Name = savedValues.getString("p1Name", "player1" );

        p2Name = (TextView) view.findViewById(R.id.displayPlayer2Name);
        p2Name.setText(savedValues.getString("p2Name", "player2" ));
        player2Name = savedValues.getString("p2Name", "player2" );

        rollBtn = (Button) view.findViewById(R.id.rollBtn);
        rollBtn.setOnClickListener(this);

        endTurnBtn = (Button) view.findViewById(R.id.endTurnBtn);
        endTurnBtn.setOnClickListener(this);

        currentScoreLabel = (TextView) view.findViewById(R.id.curPointsId);
        whosTurn = (TextView) view.findViewById(R.id.curPlayerTurnId);
        p1ScoreLabel = (TextView) view.findViewById(R.id.p1ScoreId);
        p2ScoreLabel = (TextView) view.findViewById(R.id.p2ScoreId);
        DieImage = (ImageView) view.findViewById(R.id.dieImageId);
        winner = (TextView) view.findViewById(R.id.winnerTextId);

        p1Score = 0;
        p2Score = 0;
        playerTurn = 0;

        p1ScoreLabel.setText(Integer.toString(p1Score));
        p2ScoreLabel.setText(Integer.toString(p2Score));


        return view;
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor edit = savedValues.edit();
        edit.putString("p1Name", player1Name);
        edit.putString("p2Name", player2Name);
        edit.putInt("currentScore", currentScore);
        edit.putInt("p1Score", p1Score);
        edit.putInt("p2Score", p2Score);
        edit.putInt("playerTurn", playerTurn);

        edit.commit();

        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

        // get saved values
        player1Name = savedValues.getString("player1Name", player1Name);
        player2Name = savedValues.getString("player2Name", player2Name);
        currentScore = savedValues.getInt("currentScore", currentScore);
        p1Score = savedValues.getInt("p1Score", p1Score);
        p2Score = savedValues.getInt("p2Score", p2Score);
        playerTurn = savedValues.getInt("playerTurn", playerTurn);

        // set saved values
        p1Name.setText(player1Name);
        p2Name.setText(player2Name);
        currentScoreLabel.setText(Integer.toString(currentScore));
        p1ScoreLabel.setText(Integer.toString(p1Score));
        p2ScoreLabel.setText(Integer.toString(p2Score));



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollBtn:
                rollVal = (rand.nextInt(6) + 1);
                displayImage(rollVal);
                if (rollVal == 1) {
                    currentScore = 0;
                    playerTurn += 1;
                    break;
                }
                currentScore += rollVal;
                break;

            case R.id.endTurnBtn:
                endTurn();

                break;
        }
        determineWinner();

    }

    private void endTurn() {
        if (playerTurn == 0) {
            p1Score += currentScore;
            p1ScoreLabel.setText(Integer.toString(p1Score));
        } else {
            p2Score += currentScore;
            p2ScoreLabel.setText(Integer.toString(p2Score));
        }
        currentScore = 0;
        playerTurn += 1;
    }

    private void determineWinner() {
        if (p1Score >= winningScore && p2Score < winningScore) {
            winner.setText(player1Name);
        }
        if (p2Score >= winningScore && p1Score < winningScore) {
            winner.setText(player2Name);
        }
        currentScoreLabel.setText(Integer.toString(currentScore));
        if (playerTurn > 1) {
            playerTurn = 0;
        }
        getPlayerTurn(playerTurn);

    }

    private void getPlayerTurn(int whosTurnValue) {
        if (whosTurnValue == 0) {
            whosTurn.setText(player1Name);
        } else {
            whosTurn.setText(player2Name);
        }
    }

    // display Die after roll button
    private void displayImage(int rollValue) {
        int id = 0;
        switch (rollValue) {
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



}
