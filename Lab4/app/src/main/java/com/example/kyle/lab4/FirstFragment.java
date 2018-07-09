package com.example.kyle.lab4;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FirstFragment extends Fragment implements OnClickListener {

    private EditText player1Name;
    private EditText player2Name;
    private FirstActivity activity;
    private boolean twoPaneLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        // Set this fragment to listen for the Play button's click event
        Button b = (Button) view.findViewById(R.id.startGameBtn);
        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a references from the host activity
        activity = (FirstActivity)getActivity();
        player1Name = (EditText) activity.findViewById(R.id.player1NameId);
        player2Name = (EditText) activity.findViewById(R.id.player2NameId);


        // Check to see if FirstActivity has loaded a single or dual pane layout
        //twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startGameBtn) {
            String p1Name = player1Name.getText().toString();
            String p2Name = player2Name.getText().toString();


            Intent intent = new Intent(getActivity(), SecondActivity.class);
            intent.putExtra("p1Name", p1Name);
            intent.putExtra("p2Name", p2Name);
            startActivity(intent);

        }
    }
}
