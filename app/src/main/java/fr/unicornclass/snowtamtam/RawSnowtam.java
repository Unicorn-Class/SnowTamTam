package fr.unicornclass.snowtamtam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

public class RawSnowtam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_snowtam);
        Toolbar toolbar = findViewById(R.id.rawToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TextView t = findViewById(R.id.rawSnowtam);
        t.setText(intent.getStringExtra("raw"));
    }

}
