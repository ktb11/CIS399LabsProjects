package com.example.kyle.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText locationEditText;
    private EditText dateEditText;
    private Button tideButton;
    private Dal dal = new Dal(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dal.loadDb("florance");
        locationEditText = (EditText) findViewById(R.id.locationEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        tideButton = (Button) findViewById(R.id.tidesButton);
        tideButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.tidesButton){
            String date = dateEditText.getText().toString();
            String location = locationEditText.getText().toString();

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("date", "2018/07/15");
            intent.putExtra("location","florance");
            startActivity(intent);
        }
    }
}
