package model;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import fr.unicornclass.snowtamtam.R;

/**
 * Our representation of a SNOWTAM
 * @author Victor Morgant
 * @version 1.O
 */
public class Snowtam {
    private Airport placeAirport;
    private String ObservationDate;
    private Runway runway;

    /**
     * @param placeAirport    the localisation of the airport
     * @param observationDate the SNOWTAM date
     * @param runway          the runway informations
     */
    public Snowtam(Airport placeAirport, String observationDate, Runway runway) {
        this.placeAirport = placeAirport;
        ObservationDate = observationDate;
        this.runway = runway;
    }

    public Airport getPlaceAirport() {
        return placeAirport;
    }

    public void setPlaceAirport(Airport placeAirport) {
        this.placeAirport = placeAirport;
    }

    public String getObservationDate(Context c) {
        String humanReadableDate = ObservationDate.substring(2,4)+" ";
        String month = ObservationDate.substring(0,2);
        switch (month) {
            case "01":
                humanReadableDate += c.getResources().getString(R.string.month_January);
                break;
            case "02":
                humanReadableDate += c.getResources().getString(R.string.month_February);
                break;
            case "03":
                humanReadableDate += c.getResources().getString(R.string.month_March);
                break;
            case "04":
                humanReadableDate += c.getResources().getString(R.string.month_April);
                break;
            case "05":
                humanReadableDate += c.getResources().getString(R.string.month_May);
                break;
            case "06":
                humanReadableDate += c.getResources().getString(R.string.month_June);
                break;
            case "07":
                humanReadableDate += c.getResources().getString(R.string.month_July);
                break;
            case "08":
                humanReadableDate += c.getResources().getString(R.string.month_August);
                break;
            case "09":
                humanReadableDate += c.getResources().getString(R.string.month_September);
                break;
            case "10":
                humanReadableDate += c.getResources().getString(R.string.month_October);
                break;
            case "11":
                humanReadableDate += c.getResources().getString(R.string.month_November);
                break;
            case "12":
                humanReadableDate += c.getResources().getString(R.string.month_December);
                break;
        }
        humanReadableDate+=" "+c.getResources().getString(R.string.date_At)+" "+ObservationDate.substring(4,6)+"h"+ObservationDate.substring(6);
        return humanReadableDate;
    }

    public void setObservationDate(String observationDate) {
        ObservationDate = observationDate;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public Snowtam(String codedSnowtam, Context context) {
        String[] parsedSnowtam = codedSnowtam.split("\n");
        HashMap<String,String> blocs = new HashMap<String, String>();
        for (int i = 4; i < parsedSnowtam.length; i++){
            String line = parsedSnowtam[i];
            if (line.charAt(1) == ')'){
                String words[] = line.split(" ");
                for (int j = 0; j < words.length; j = j+2) {
                    if (!words[j].equals("N)") && !words[j].equals("R)") && !words[j].equals("T)")) {
                        Log.d("WORD 1",words[j]);
                        Log.d("WORD 2",words[j+1]);
                        blocs.put(words[j],words[j+1]);
                    } else {
                        break;
                    }
                }
            }
        }

        this.placeAirport = new Airport(blocs.get("A)"),context);
        this.ObservationDate = blocs.get("B)");
        this.runway=new Runway(blocs);
    }

}