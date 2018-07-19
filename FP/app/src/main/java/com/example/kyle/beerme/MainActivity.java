package com.example.kyle.beerme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etBeerName, etBreweryName, etDate;
    Button addBtn, goToDbBtn;
    DatabaseHelper myDB;
    Spinner spinType;
    RatingBar beerRating;

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
                String beerName = etBeerName.getText().toString();
                String breweryName = etBreweryName.getText().toString();
                String date = etDate.getText().toString();
                String type = spinType.getSelectedItem().toString();
                String rating = Integer.toString(beerRating.getNumStars());

                if (beerName.length() != 0 && breweryName.length() != 0 && date.length() != 00){
                    addData(beerName,breweryName,date,type, rating);
                    etBeerName.setText("");
                    etBreweryName.setText("");
                    etDate.setText("");
                } else{
                    Toast.makeText(MainActivity.this, "must add data",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void addData(String beerName, String breweryName, String date, String type, String rating){
        boolean insertData = myDB.addData(beerName,breweryName,date,type, rating);

        if(insertData == true){
            Toast.makeText(MainActivity.this, "data inserted",Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(MainActivity.this, "data insertion error",Toast.LENGTH_LONG).show();
        }
    }
}
