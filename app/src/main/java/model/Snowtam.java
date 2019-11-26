package model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public String getObservationDate() {
        String humanReadableDate = ObservationDate.substring(2,4)+" ";
        String month = ObservationDate.substring(0,2);
        switch (month) {
            case "01":
                humanReadableDate += R.string.month_January;
                break;
            case "02":
                humanReadableDate += R.string.month_February;
                break;
            case "03":
                humanReadableDate += R.string.month_March;
                break;
            case "04":
                humanReadableDate += R.string.month_April;
                break;
            case "05":
                humanReadableDate += R.string.month_May;
                break;
            case "06":
                humanReadableDate += R.string.month_June;
                break;
            case "07":
                humanReadableDate += R.string.month_July;
                break;
            case "08":
                humanReadableDate += R.string.month_August;
                break;
            case "09":
                humanReadableDate += R.string.month_September;
                break;
            case "10":
                humanReadableDate += R.string.month_October;
                break;
            case "11":
                humanReadableDate += R.string.month_November;
                break;
            case "12":
                humanReadableDate += R.string.month_December;
                break;
        }
        humanReadableDate+=" "+R.string.date_At+" "+ObservationDate.substring(4,6)+"h"+ObservationDate.substring(6);
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
        /*
        String[] parsedSnowtam = codedSnowtam.split("(?=\\) )",3);
        this.placeAirport=new Airport(parsedSnowtam[1],context);
        this.ObservationDate=parsedSnowtam[2];
        this.runway=new Runway(parsedSnowtam[3]);
        */
        /*codedSnowtam = codedSnowtam.replace("   "," ");
        codedSnowtam = codedSnowtam.replace("\n"," ");
        codedSnowtam = codedSnowtam.replace("\r"," ");
        codedSnowtam = codedSnowtam.replace("\t"," ");*/
        String[] parsedSnowtam = codedSnowtam.split("\n");
        this.placeAirport = new Airport(parsedSnowtam[4].split(" ")[1],context);
        this.ObservationDate = parsedSnowtam[5].split(" ")[1];
        this.runway=new Runway(parsedSnowtam[5].split(" ")[2]+" "+parsedSnowtam[5].split(" ")[3]+" "+parsedSnowtam[6]);
    }

}