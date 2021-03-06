package com.example.kyle.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;
import android.preference.PreferenceManager;

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
    private String player1Name = "";
    private String player2Name = "";
    private String player2NameStorage;
    private String computerString = "computer";

    // settings
    boolean showImg;
    private int winningScore;
    private int dieValue;
    private boolean enableAI;


    private Random rand = new Random();

    // define SharedPreferences Object
    private SharedPreferences saveVals;
    private SharedPreferences prefs;

    // need to implement class
    //PigGame game = new PigGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referring widgets
        rollDie = (Button) findViewById(R.id.rollBtn);
        endTurn = (Button) findViewById(R.id.endTurnBtn);
        turnPoints = (TextView) findViewById(R.id.turnPointsId);
        DieImage = (ImageView) findViewById(R.id.dieImgId);
        player1Score = (TextView) findViewById(R.id.player1ScoreId);
        player2Score = (TextView) findViewById(R.id.player2ScoreId);
        whosTurn = (TextView) findViewById(R.id.curPlayerNameID);
        winner = (TextView) findViewById(R.id.winnerTextView);
        p1Name = (EditText) findViewById(R.id.player1NameId);
        p2Name = (EditText) findViewById(R.id.player2NameId);
        newGame = (Button) findViewById(R.id.newGameBtn);


        // set listeners
        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);
        newGame.setOnClickListener(this);
        p1Name.setOnEditorActionListener(this);
        p2Name.setOnEditorActionListener(this);

        // saving data
        saveVals = getSharedPreferences("SaveVals", MODE_PRIVATE);

        // preferences
        PreferenceManager.setDefaultValues(this,R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public void onPause() {
        Editor edit = saveVals.edit();
        edit.putString("player1Name", player1Name);
        edit.putString("player2Name", player2Name);
        edit.putInt("p1TotalPoints", p1TotalPoints);
        edit.putInt("p2TotalPoints", p2TotalPoints);
        edit.putInt("playerTurn", playerTurn);
        edit.putInt("curPoints", curPoints);
        edit.commit();

        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

        // get saved values
        player1Name = saveVals.getString("player1Name", player1Name);
        player2Name = saveVals.getString("player2Name", player2Name);
        p1TotalPoints = saveVals.getInt("p1TotalPoints", p1TotalPoints);
        p2TotalPoints = saveVals.getInt("p2TotalPoints", p2TotalPoints);
        playerTurn = saveVals.getInt("playerTurn", playerTurn);
        curPoints = saveVals.getInt("curPoints", curPoints);

        // set saved values
        p1Name.setText(player1Name);
        p2Name.setText(player2Name);
        player1Score.setText(Integer.toString(p1TotalPoints));
        player2Score.setText(Integer.toString(p2TotalPoints));
        getPlayerTurn(playerTurn);
        turnPoints.setText(Integer.toString(curPoints));

        // prefs
        showImg = prefs.getBoolean(getResources().getString(R.string.pref_show_images),true);
        if (!showImg){
            DieImage.setVisibility(View.GONE);
        }
        else
            DieImage.setVisibility(View.VISIBLE);

        winningScore = Integer.parseInt(prefs.getString("pref_winning_score", "100" ));
        dieValue = Integer.parseInt(prefs.getString("pref_num_die_sides", "6"));
        enableAI = prefs.getBoolean(getResources().getString(R.string.pref_enable_ai), false);
        if (enableAI){
            player2NameStorage = player2Name;
            player2Name = "computer";
            p2Name.setText(player2Name);
        }
        else {
            player2Name = player2NameStorage;
            p2Name.setText(player2Name);
            getPlayerTurn(playerTurn);

        }

    }

    @Override
    public void onClick(View v) {
        int id = 0;

        switch (v.getId()) {
            case R.id.rollBtn:
                rollVal = (rand.nextInt(dieValue) + 1);
                displayImage(rollVal);
                if (rollVal == 1) {
                    Toast.makeText(this, "You rolled a 1! Zero points!", Toast.LENGTH_LONG).show();
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
            case 7:
                id = R.drawable.die7;
                break;
            case 8:
                id = R.drawable.die8;
                break;
        }
        DieImage.setImageResource(id);
    }

    private void getPlayerTurn(int whosTurnValue) {
        if (whosTurnValue == 0) {
            whosTurn.setText(player1Name);
        } else {
            whosTurn.setText(player2Name);
        }
    }

    private void determineWinner() {
        // ai code
        if (enableAI){
            if(playerTurn == 1){
                boolean keepRolling = true;
                int targetScore = 10;
                while (keepRolling && curPoints < targetScore){
                    rollVal = (rand.nextInt(dieValue) + 1);
                    curPoints += rollVal;
                    if (rollVal == 1) {
                        Toast.makeText(this, "Computer rolled a 1! Zero points!", Toast.LENGTH_LONG).show();
                        curPoints = 0;
                        keepRolling = false;
                        playerTurn += 1;
                        //break;
                    }
                    //curPoints += rollVal;

                }
                endTurn();


            }
        }
        if (p1TotalPoints >= winningScore && p2TotalPoints < winningScore) {
            winner.setText(player1Name);
            Toast.makeText(this, player1Name + " has won", Toast.LENGTH_LONG).show();
            isWinner = true;
        }
        if (p2TotalPoints >= winningScore && p1TotalPoints < winningScore) {
            winner.setText(player2Name);
            Toast.makeText(this, player2Name + " has won", Toast.LENGTH_LONG).show();
            isWinner = true;
        }
        turnPoints.setText(Integer.toString(curPoints));
        if (playerTurn > 1) {
            playerTurn = 0;
        }
        getPlayerTurn(playerTurn);

    }

    private void endTurn() {
        if (playerTurn == 0) {
            p1TotalPoints += curPoints;
            player1Score.setText(Integer.toString(p1TotalPoints));
        } else {
            p2TotalPoints += curPoints;
            player2Score.setText(Integer.toString(p2TotalPoints));
        }
        curPoints = 0;
        playerTurn += 1;
    }

    private void newGame() {
        playerTurn = 0;
        p1TotalPoints = 0;
        p2TotalPoints = 0;
        curPoints = 0;
        player1Score.setText(Integer.toString(p1TotalPoints));
        player2Score.setText(Integer.toString(p2TotalPoints));
        winner.setText("waiting");

    }

    private boolean checkAITurn(){
        if (playerTurn == 1)
            enableAI = true;
        else
            enableAI = false;
        return enableAI;
    }

    private void computerTurn(){

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

    /* -------- Activity Callback Methods for the Menu ------- */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            startActivity(new Intent(
                    getApplicationContext(), SettingsActivity.class));
            return true;
        }

        if (id == R.id.menu_about) {
            Toast.makeText(this, "This game was written by Kyle Bennett", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

}
