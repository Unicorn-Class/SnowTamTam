package fr.unicornclass.snowtamtam.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import fr.unicornclass.snowtamtam.R;

/**
 * Our representation of a SNOWTAM
 * @author Victor Morgant
 * @version 1.O
 */
public class Snowtam {
    private Airport placeAirport;


    private ArrayList<Runway> runways = new ArrayList<>();

    /**
     * @param placeAirport    the localisation of the airport
     */
    public Snowtam(Airport placeAirport) {
        this.placeAirport = placeAirport;
    }

    public Airport getPlaceAirport() {
        return placeAirport;
    }

    public Snowtam(String codedSnowtam, Context context) {
        String[] parsedSnowtam = codedSnowtam.split("\n");
        ArrayList<HashMap<String,String>> runwaysSnowtams = new ArrayList<HashMap<String,String>>();
        int nbB = 0;
        String airportOACI = "";
        HashMap<String,String> blocs = new HashMap<String, String>();
        for (int i = 4; i < parsedSnowtam.length; i++){
            String line = parsedSnowtam[i];
            if (line.charAt(1) == ')'){
                String words[] = line.split(" ");
                for (int j = 0; j < words.length; j = j+2) {
                    if (!words[j].equals("N)") && !words[j].equals("R)") && !words[j].equals("T)")) {
                        if (words[j].equals("A)")) airportOACI = words[j+1];
                        else {
                            if (words[j].equals("B)")) {
                                if (nbB > 0) runwaysSnowtams.add(blocs);
                                blocs = new HashMap<>();
                                nbB++;
                            }
                            blocs.put(words[j],words[j+1]);
                        }
                    } else {
                        break;
                    }
                }

            }
        }
        runwaysSnowtams.add(blocs);
        this.placeAirport = Airport.getAirport(airportOACI,context);
        for (HashMap<String,String> blocks : runwaysSnowtams){
            Runway r = new Runway(blocks);
            this.runways.add(r);
        }
    }

    public ArrayList<Runway> getRunways() {
        return runways;
    }

    public void setRunways(ArrayList<Runway> runways) {
        this.runways = runways;
    }
}