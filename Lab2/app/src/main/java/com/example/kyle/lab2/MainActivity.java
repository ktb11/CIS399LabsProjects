package com.example.kyle.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // define variables for widgets
    private Button rollDie;
    private Button endTurn;
    private TextView turnPoints;
    private int curPoints = 0;

    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDie = (Button)findViewById(R.id.rollBtn);
        endTurn = (Button)findViewById(R.id.endTurnBtn);
        turnPoints = (TextView)findViewById(R.id.turnPointsId);

        // set listeners
        rollDie.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollBtn:
                curPoints += (rand.nextInt(6)+1);

                break;
            case R.id.endTurnBtn:
                break;
        }
    }
}
