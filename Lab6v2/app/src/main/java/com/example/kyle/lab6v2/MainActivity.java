package com.example.kyle.lab6v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText locationEditText;
    private EditText dateEditText;
    private Button showButton;

    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationEditText = (EditText) findViewById(R.id.locationEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        showButton = (Button) findViewById(R.id.showTidesButton);
        showButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showTidesButton) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);

        }
    }
}
