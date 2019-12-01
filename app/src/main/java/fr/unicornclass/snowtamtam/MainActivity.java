package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;

import model.Airport;

public class MainActivity extends AppCompatActivity {

    public Airport a1 = new Airport("LFPG","Paris Charles de Gaulle","France",49.007951,2.542880);
    public Airport a2 = new Airport("ENGM","Oslo-Gardermoen","Norway",60.196674,11.100154);
    public Airport a3 = new Airport("ENBR","Bergen Airport","Norway",60.290763,5.222252);
    public Airport a4 = new Airport("ENZV","Aéroport de Stavanger","Norway",58.881897,5.629510);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiYW1hY3oxMyIsImEiOiJjazM3MjNvOWkwN2JjM25saWRkOWJjaWQ3In0.QWLf0Qgc_5m6qEsqZyUNfQ");
        setContentView(R.layout.activity_main);
        FloatingActionButton searchFab = findViewById(R.id.searchFab);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
            }
        });

        Context context = this.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("storage", Context.MODE_PRIVATE);

        CardView card = findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowAirportGroup.class);
                intent.putExtra("nbAirports",4);
                intent.putExtra("airport1",a1);
                intent.putExtra("airport2",a2);
                intent.putExtra("airport3",a3);
                intent.putExtra("airport4",a4);
                startActivity(intent);
            }
        });
    }


}
