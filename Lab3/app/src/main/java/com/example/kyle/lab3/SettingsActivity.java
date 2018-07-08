package com.example.kyle.lab3;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {
    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // addPreferencesFromResource is deprecated.
        // The preferred technique is to put preferences in a Fragment
        // TODO: We'll use a PreferenceFragment in the next version of this app
        addPreferencesFromResource(R.xml.preferences);
    }
}
