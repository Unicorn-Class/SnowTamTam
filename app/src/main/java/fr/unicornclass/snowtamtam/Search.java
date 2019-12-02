package fr.unicornclass.snowtamtam;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import model.Airport;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText searchBar = findViewById(R.id.searchBar);
        final Context c = getApplicationContext();
        final CardView card = findViewById(R.id.searchResult);
        card.setVisibility(View.INVISIBLE);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (searchBar.getText().toString().length() == 4){
                    try {
                        final Airport airport = new Airport(searchBar.getText().toString().toUpperCase(),c);
                        TextView oaci = findViewById(R.id.airportOACI);
                        TextView name = findViewById(R.id.airportFriendlyName);
                        oaci.setText(airport.getOaciCode());
                        name.setText(airport.getName());

                        Button b = findViewById(R.id.buttonAddToGrp);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        card.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        card.setVisibility(View.INVISIBLE);
                    }
                } else {
                    card.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void addAirportToGroup(Airport a){
        ConstraintLayout l = findViewById(R.id.listAirports);
        CardView card = new CardView(getApplicationContext());
    }

}
