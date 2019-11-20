package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYW1hY3oxMyIsImEiOiJjazM3MjNvOWkwN2JjM25saWRkOWJjaWQ3In0.QWLf0Qgc_5m6qEsqZyUNfQ");
        setContentView(R.layout.activity_main);
        FloatingActionButton searchFab = (FloatingActionButton) findViewById(R.id.searchFab);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),Search.class);
            startActivity(intent);
            }
        });

        CardView card = findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowAirportGroup.class);
                startActivity(intent);
            }
        });
    }


}
