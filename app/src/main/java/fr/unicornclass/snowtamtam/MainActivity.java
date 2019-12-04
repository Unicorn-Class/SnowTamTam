package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;

import model.Airport;

public class MainActivity extends AppCompatActivity {

    public Airport a1 = new Airport("LFPG","Paris Charles de Gaulle","France",49.007951,2.542880);
    public Airport a2 = new Airport("ENGM","Oslo-Gardermoen","Norway",60.196674,11.100154);
    public Airport a3 = new Airport("ENBR","Bergen Airport","Norway",60.290763,5.222252);
    public Airport a4 = new Airport("ENZV","Aéroport de Stavanger","Norway",58.881897,5.629510);


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String groups = sharedPref.getString("groups",null);
                Log.d("Storage",groups==null?"NO GROUPS":groups);
            }
        }
    }

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
                startActivityForResult(intent, 1);
            }
        });

        Context context = this.getApplicationContext();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String groups = sharedPref.getString("groups",null);
        showGroups(groups);
        Log.d("Storage",groups==null?"NO GROUPS":groups);
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

    public void showGroups(String groups){
        TableLayout table = findViewById(R.id.listGroups);
        CardView card = new CardView(getApplicationContext());
        TextView txt = new TextView(getApplicationContext());
        ViewGroup.LayoutParams clp = card.getLayoutParams();
        clp.height = 64;
    }

}
