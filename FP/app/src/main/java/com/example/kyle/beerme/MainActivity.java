package com.example.kyle.beerme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener{

    EditText etBeerName, etBreweryName, etDate;
    Button addBtn, goToDbBtn;
    DatabaseHelper myDB;
    Spinner spinType;
    RatingBar beerRating;

    String beerName, breweryName, date, type, rating;

    // save values manager
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBeerName = (EditText) findViewById(R.id.beerNameEditText);
        etBreweryName =(EditText) findViewById(R.id.breweryNameEditText);
        etDate = (EditText) findViewById(R.id.dateEditText);
        addBtn = (Button) findViewById(R.id.addBtn);
        goToDbBtn = (Button) findViewById(R.id.goToDbBtn);
        spinType = (Spinner) findViewById(R.id.spinnerType);
        beerRating = (RatingBar) findViewById(R.id.ratingBar);
        myDB = new DatabaseHelper(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        goToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beerName = etBeerName.getText().toString();
                breweryName = etBreweryName.getText().toString();
                date = etDate.getText().toString();
                type = spinType.getSelectedItem().toString();
                rating = Float.toString(beerRating.getRating());

                if (beerName.length() != 0 && breweryName.length() != 0 && date.length() != 0){
                    addData(beerName,breweryName,date,type, rating);
                    etBeerName.setText("");
                    etBreweryName.setText("");
                    etDate.setText("");
                } else{
                    Toast.makeText(MainActivity.this, "Must add data",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void addData(String beerName, String breweryName, String date, String type, String rating){
        boolean insertData = myDB.addData(beerName,breweryName,date,type, rating);

        if(insertData == true){
            Toast.makeText(MainActivity.this, "Data inserted",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(MainActivity.this, "Data insertion error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            switch (view.getId()) {
                case R.id.beerNameEditText:
                    beerName = etBeerName.getText().toString();
                    break;
                case R.id.breweryNameEditText:
                    breweryName = etBreweryName.getText().toString();
                    break;
                case R.id.dateEditText:
                    date = etDate.getText().toString();
                    break;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("beerName", etBeerName.getText().toString());
        editor.putString("breweryName", etBreweryName.getText().toString());
        editor.putString("date", etDate.getText().toString());
        //editor.putString("type", spinType.getSelectedItem().toString());
        //editor.putString("rating",Float.toString(beerRating.getRating())) ;
        editor.commit();
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        etBeerName.setText(savedValues.getString("beerName", beerName));
        etBreweryName.setText(savedValues.getString("breweryName", breweryName));
        etDate.setText(savedValues.getString("date", date));
        //spinType.set();
        //beerRating.setRating(Float.parseFloat("rating"));
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
        } else if (id == R.id.clear_db) {
            myDB.getWritableDatabase().delete(myDB.TABLE_NAME,null,null);
            Toast.makeText(this, "Clearing Database", Toast.LENGTH_LONG).show();
            return true;

        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }
}
