package fr.unicornclass.snowtamtam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.unicornclass.snowtamtam.model.Airport;

public class Search extends AppCompatActivity {

    public ArrayList<Airport> group = new ArrayList<>();


    CardView airportCard1;
    CardView airportCard2;
    CardView airportCard3;
    CardView airportCard4;


    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        airportCard1 = findViewById(R.id.airport1);
        airportCard2 = findViewById(R.id.airport2);
        airportCard3 = findViewById(R.id.airport3);
        airportCard4 = findViewById(R.id.airport4);


        txt1 = findViewById(R.id.airportOACI1);
        txt2 = findViewById(R.id.airportOACI2);
        txt3 = findViewById(R.id.airportOACI3);
        txt4 = findViewById(R.id.airportOACI4);

        FloatingActionButton fab = findViewById(R.id.createGrpBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (group.size() > 0) {
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String groups = sharedPref.getString("groups", null);
                    if (groups == null) {
                        groups = "";
                    }
                    for (Airport a : group) {
                        groups += a.getOaciCode() + "/";
                    }
                    groups = groups.substring(0, groups.length() - 1);
                    groups += "@";
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("groups", groups);
                    editor.commit();
                    Intent data = new Intent();
                    data.setData(Uri.parse(groups));
                    setResult(1,data);
                    finish();
                }
            }
        });

        final EditText searchBar = findViewById(R.id.searchBar);
        final Context c = getApplicationContext();
        final CardView card = findViewById(R.id.searchResult);
        card.setVisibility(View.GONE);

        airportCard1.setVisibility(View.GONE);

        airportCard2.setVisibility(View.GONE);

        airportCard3.setVisibility(View.GONE);

        airportCard4.setVisibility(View.GONE);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (searchBar.getText().toString().length() == 4){
                    try {
                        final Airport airport = Airport.getAirport(searchBar.getText().toString().toUpperCase(),c);
                        TextView oaci = findViewById(R.id.airportOACI);
                        TextView name = findViewById(R.id.airportFriendlyName);
                        oaci.setText(airport.getOaciCode());
                        name.setText(airport.getName());

                        Button b = findViewById(R.id.buttonAddToGrp);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                addAirportToGroup(airport);
                                card.setVisibility(View.GONE);
                                searchBar.setText("");
                            }
                        });

                        card.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        card.setVisibility(View.GONE);
                        searchBar.setError(getString(R.string.invalidICAO));
                    }
                } else {
                    card.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void removeAirportFromGroup(final Airport a){
        group.remove(a);
        ArrayList<Airport> newGroup = (ArrayList<Airport>) group.clone();
        group.clear();

        airportCard1.setVisibility(View.GONE);
        airportCard2.setVisibility(View.GONE);
        airportCard3.setVisibility(View.GONE);
        airportCard4.setVisibility(View.GONE);

        for (Airport ap : newGroup) addAirportToGroup(ap);
    }

    public void addAirportToGroup(final Airport a){
        if (group.contains(a)) {
            Toast.makeText(this.getApplicationContext(),getString(R.string.alreadyInGroup),Toast.LENGTH_LONG).show();
            return;
        }
        if (group.size() < 4) {
            group.add(a);
            switch (group.size()){
                case 1:
                    airportCard1.setVisibility(View.VISIBLE);
                    txt1.setText(a.getOaciCode());
                    final Button rm1 = findViewById(R.id.airportBtnRm1);
                    rm1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAirportFromGroup(a);
                        }
                    });
                    break;
                case 2:
                    txt2.setText(a.getOaciCode());
                    airportCard2.setVisibility(View.VISIBLE);
                    final Button rm2 = findViewById(R.id.airportBtnRm2);
                    rm2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAirportFromGroup(a);
                        }
                    });
                    break;
                case 3:
                    txt3.setText(a.getOaciCode());
                    airportCard3.setVisibility(View.VISIBLE);
                    final Button rm3 = findViewById(R.id.airportBtnRm3);
                    rm3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAirportFromGroup(a);
                        }
                    });
                    break;
                case 4:
                    txt4.setText(a.getOaciCode());
                    airportCard4.setVisibility(View.VISIBLE);
                    final Button rm4 = findViewById(R.id.airportBtnRm4);
                    rm4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeAirportFromGroup(a);
                        }
                    });
                    break;
            }
        } else {
            Toast.makeText(this.getApplicationContext(),getString(R.string.cantAddMore4),Toast.LENGTH_LONG).show();
        }
    }

}
