package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle(getString(R.string.help));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
