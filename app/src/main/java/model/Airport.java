package model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Our representation of an airport
 * @author Victor Morgant
 * @version 1.O
 */
public class Airport implements Serializable {
    private String oaciCode;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    public static HashMap<String,Airport> cached = new HashMap<>();

    /**
     * @param oaciCode the OACI code of the airport should be 4 letters
     * @param name the name of the airport
     * @param country the country of the airport
     * @param latitude the latitude of the airport
     * @param longitude the longitude of the airport
     */
    private Airport(String oaciCode, String name, String country, double latitude, double longitude) {
        this.oaciCode = oaciCode;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Airport getAirport(String oaci,Context context){
        if (cached.containsKey(oaci)) return cached.get(oaci);
        Airport a = new Airport(oaci,context);
        cached.put(oaci,a);
        return a;
    }

    private Airport(String oaci,Context context) {
        this.oaciCode=oaci;
        JSONObject airport = getAirportFromOACI(oaci,context);
        this.oaciCode=oaci;
        Log.d("OACI Code",this.oaciCode);
        try {
            this.name=airport.getString("Name");
            this.country=airport.getString("Country");
            this.latitude=(float)airport.getDouble("Latitude");
            this.longitude=(float)airport.getDouble("Longitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getAirportFromOACI(String oaci,Context context) {
        try {
            InputStream is = context.getAssets().open("airport.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONArray aiportList = new JSONArray(json);
            for (int i = 0; i < aiportList.length(); i++) {
                JSONObject airport = aiportList.getJSONObject(i);
                if(airport.getString("ICAO").equals(oaci)){
                    return airport;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getOaciCode() {
        return oaciCode;
    }

    public void setOaciCode(String oaciCode) {
        this.oaciCode = oaciCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
