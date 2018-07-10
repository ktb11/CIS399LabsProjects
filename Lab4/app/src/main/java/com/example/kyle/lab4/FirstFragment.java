package com.example.kyle.lab4;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FirstFragment extends Fragment implements OnClickListener, EditText.OnEditorActionListener {

    private EditText p1Name;
    private EditText p2Name;

    private String player1Name;
    private String player2Name;
    private int p1Score;
    private int p2Score;

    private FirstActivity activity;
    private boolean twoPaneLayout;

    // Preferences and saved data
    private SharedPreferences savedValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // Set this fragment to listen for the Play button's click event
        Button b = (Button) view.findViewById(R.id.startGameBtn);
        b.setOnClickListener(this);

        p1Name = (EditText) view.findViewById(R.id.player1NameId);
        p2Name = (EditText) view.findViewById(R.id.player2NameId);

        p1Name.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("p1Name", p1Name.getText().toString());
        editor.putString("p2Name", p2Name.getText().toString());
        editor.commit();

        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        p1Name.setText(savedValues.getString("p1Name", player1Name));
        p2Name.setText(savedValues.getString("p2Name", player2Name));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a references from the host activity
        activity = (FirstActivity)getActivity();


        // Check to see if FirstActivity has loaded a single or dual pane layout
        //twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startGameBtn) {



            Intent intent = new Intent(getActivity(), SecondActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
        SharedPreferences.Editor editor = savedValues.edit();
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            switch (view.getId()) {
                case R.id.player1NameId:
                    editor.putString("p1Name", p1Name.getText().toString());
                    break;
                case R.id.player2NameId:
                    editor.putString("p2Name", p2Name.getText().toString());
                    break;
            }
            editor.commit();
            return true;
        }
        return false;
    }


}
