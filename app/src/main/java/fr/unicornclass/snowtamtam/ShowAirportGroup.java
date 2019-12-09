package fr.unicornclass.snowtamtam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.unicornclass.snowtamtam.model.Airport;

public class ShowAirportGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_airport_group);
        Intent intent = getIntent();
        int nb = intent.getIntExtra("nbAirports",1);
        Log.d("nbAirports",""+nb);
        List<Airport> listAirport = new ArrayList<Airport>();
        String gpName = "";
        for (int i = 1; i < nb+1; i++){
            Airport a = (Airport) intent.getSerializableExtra("airport"+i);
            Log.d("AddAp",a.getOaciCode());
            listAirport.add(a);
            gpName+= a.getOaciCode()+" ";
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), listAirport);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(gpName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}