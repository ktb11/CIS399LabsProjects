package com.example.kyle.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // define variables for the widgets
    private TextView counterTextView;
    private Button addButton;
    private Button resetButton;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to the widgets
        addButton = (Button) findViewById(R.id.addBtnId);
        resetButton = (Button) findViewById(R.id.resetBtnId);
        counterTextView = (TextView) findViewById(R.id.counterId);

        // set the listeners
        addButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtnId:
                count += 1;
                counterTextView.setText(Integer.toString(count));
                break;
            case R.id.resetBtnId:
                count = 0;
                counterTextView.setText(Integer.toString(count));
                break;
        }
    }
}
