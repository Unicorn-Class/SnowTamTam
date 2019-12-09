package fr.unicornclass.snowtamtam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;

import java.util.ArrayList;

import fr.unicornclass.snowtamtam.model.Airport;

public class MainActivity extends AppCompatActivity {


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String groups = sharedPref.getString("groups",null);
        LinearLayout table = findViewById(R.id.listGroups);
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
        showGroups(groups);
    }

    public void showGroups(final String groups){
        if (groups == null || groups.equals("")) return;
        final LinearLayout table = findViewById(R.id.listGroups);

        final Context c = this;


        String[] groupsAirports = groups.split("@");

        for (final String gp : groupsAirports){
            String[] OACIs = gp.split("/");
            final ArrayList<Airport> list = new ArrayList<>();
            for (String oaci : OACIs){
                Airport a = Airport.getAirport(oaci, getApplicationContext());
                list.add(a);
            }

            CardView card = new CardView(getApplicationContext());
            TextView txt = new TextView(getApplicationContext());

            CardView.LayoutParams clp = new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            clp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics());
            clp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            int seizeDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
            clp.setMargins(seizeDP,seizeDP,seizeDP,seizeDP);
            card.setLayoutParams(clp);


            RelativeLayout.LayoutParams tlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tlp.setMargins(seizeDP,seizeDP,seizeDP,seizeDP);
            txt.setLayoutParams(tlp);
            txt.setText(gp);
            txt.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
            txt.setTextColor(getColor(R.color.colorPrimary));

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
            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    String newGroups = groups.replace(gp+"@","");
                                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("groups", newGroups);
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(),getString(R.string.groupRemoved),Toast.LENGTH_LONG).show();
                                    table.removeAllViews();
                                    showGroups(newGroups);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle(getString(R.string.airportGroupDelete)).setMessage(getString(R.string.confirmDeletion)).setPositiveButton(getString(R.string.yes), dialogClickListener).setNegativeButton(getString(R.string.no), dialogClickListener).show();
                    return true;
                }
            });
        }




    }

}
