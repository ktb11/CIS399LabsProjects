package com.example.kyle.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener, EditText.OnEditorActionListener{

    // initialize widgets
    private EditText beerNameEditText;
    private EditText breweryNameEditText;
    private Spinner beerTypeSpin;
    private EditText beerCostEditText;
    private RatingBar beerRatingBar;
    private EditText beerDateEditText;
    private Button addButton;
    private Button goToDb;

    // save values manager
    private SharedPreferences savedValues;
    private SharedPreferences prefs;

    // initialize variables
    String beerName;
    String breweryName;
    String beerType;
    String beerCost;
    int beerRating;
    String beerDate;

    BeerSQLiteHelper mBeerSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBeerSQLiteHelper = new BeerSQLiteHelper(this);

        // reference widgets
        beerNameEditText = (EditText) findViewById(R.id.beerNameId);
        breweryNameEditText = (EditText)findViewById(R.id.breweryNameId);
        beerTypeSpin = (Spinner) findViewById(R.id.beerTypeSpinnerId);
        beerCostEditText = (EditText) findViewById(R.id.beerCostId);
        beerRatingBar = (RatingBar) findViewById(R.id.ratingBarId);
        beerDateEditText = (EditText) findViewById(R.id.beerDateId);
        addButton = (Button) findViewById(R.id.addBeerId);
        goToDb = (Button) findViewById(R.id.goToBeerDbId);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        addButton.setOnClickListener(this);
        goToDb.setOnClickListener(this);

    }

    public void addBeerName(String newEntry){
        boolean insertData = mBeerSQLiteHelper.addBeerName(newEntry);
        if (insertData) {
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("beerName", beerNameEditText.getText().toString());
        editor.putString("breweryName", breweryNameEditText.getText().toString());
        editor.putString("beerType", beerTypeSpin.getSelectedItem().toString());
        editor.putString("beerCost", beerCostEditText.getText().toString());
        editor.putInt("beerRating", beerRatingBar.getNumStars());
        editor.putString("beerDate", beerDateEditText.getText().toString());
        editor.commit();
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        beerNameEditText.setText(savedValues.getString("beerName", beerName));
        breweryNameEditText.setText(savedValues.getString("breweryName", breweryName));
        //beerTypeSpin.setSelected(savedValues.getString("beerType", beerType));
        beerCostEditText.setText(savedValues.getString("beerCost", beerCost));
        beerRatingBar.setNumStars(beerRating);
        beerDateEditText.setText(savedValues.getString("beerDate", beerDate));
    }


    // event handlers
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goToBeerDbId){
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.addBeerId){
            String newBeerName = beerNameEditText.getText().toString();
            if(beerNameEditText.length() != 0){
                addBeerName(newBeerName);
                beerNameEditText.setText("");
            }
        }

    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            switch (view.getId()) {
                case R.id.beerNameId:
                    beerName = beerNameEditText.getText().toString();
                    break;
                case R.id.breweryNameId:
                    breweryName = breweryNameEditText.getText().toString();
                    break;
                case R.id.beerCostId:
                    beerCost = beerCostEditText.getText().toString();
                    break;
                case R.id.beerDateId:
                    beerDate = beerDateEditText.getText().toString();
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

        if (id == R.id.menu_about) {
            Toast.makeText(this, "This app was written by Kyle Bennett", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

}
