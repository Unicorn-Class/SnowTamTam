package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;

import java.util.ArrayList;

import model.Airport;

public class MainActivity extends AppCompatActivity {

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode,resultCode,data);
        Log.d("RETOUR","COUCOU ");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String groups = sharedPref.getString("groups",null);
        Log.d("Storage",groups==null?"NO GROUPS":groups);
        TableLayout table = findViewById(R.id.listGroups);
        table.removeAllViews();
        showGroups(groups);
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
        if (groups != null) showGroups(groups);
        //Log.d("Storage",groups==null?"NO GROUPS":groups);
    }

    public void showGroups(String groups){
        TableLayout table = findViewById(R.id.listGroups);


        String groupsAirports[] = groups.split("@");

        for (String gp : groupsAirports){
            String OACIs[] = gp.split("/");
            final ArrayList<Airport> list = new ArrayList<>();
            for (String oaci : OACIs){
                Airport a = new Airport(oaci, getApplicationContext());
                list.add(a);
            }

            CardView card = new CardView(getApplicationContext());
            TextView txt = new TextView(getApplicationContext());

            CardView.LayoutParams clp = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            clp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics());
            clp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int seizeDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
            clp.setMargins(seizeDP,seizeDP,seizeDP,seizeDP);
            card.setLayoutParams(clp);

            RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tlp.setMargins(seizeDP,seizeDP,seizeDP,seizeDP);
            txt.setLayoutParams(tlp);
            txt.setText(gp);
            txt.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));

            card.addView(txt);
            table.addView(card);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ShowAirportGroup.class);
                    intent.putExtra("nbAirports",list.size());
                    for (int i = 1; i <= list.size(); i++){
                        intent.putExtra("airport"+i,list.get(i-1));
                    }
                    startActivity(intent);
                }
            });
        }




    }

}
