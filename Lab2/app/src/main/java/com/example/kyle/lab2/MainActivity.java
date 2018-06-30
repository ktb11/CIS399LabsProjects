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
    private int curPoints = 0;
    private int rollVal;
    private ImageView DieImage;
    private TextView player1Score;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDie = (Button)findViewById(R.id.rollBtn);
        endTurn = (Button)findViewById(R.id.endTurnBtn);
        turnPoints = (TextView)findViewById(R.id.turnPointsId);
        DieImage = (ImageView)findViewById(R.id.dieImgId);
        player1Score = (TextView)findViewById(R.id.player1ScoreId);

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
                if (rollVal == 1) {
                    id = R.drawable.die1;
                    curPoints += 1;
                }

                if (rollVal == 2) {
                    id = R.drawable.die2;
                    curPoints += 2;
                }

                if (rollVal == 3){
                    id = R.drawable.die3;
                    curPoints += 3;
                }

                if (rollVal == 4){
                    id = R.drawable.die4;
                    curPoints += 4;
                }

                if (rollVal == 5){
                    id = R.drawable.die5;
                    curPoints += 5;
                }

                if (rollVal == 6){
                    id = R.drawable.die6;
                    curPoints += 6;
                }


                break;
            case R.id.endTurnBtn:
                player1Score.setText(Integer.toString(curPoints));
                curPoints = 0;

                break;
        }
        DieImage.setImageResource(id);
        turnPoints.setText(Integer.toString(curPoints));
    }
}
