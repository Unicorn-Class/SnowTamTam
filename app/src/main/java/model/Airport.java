package model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;



/**
 * Our representation of an airport
 * @author Victor Morgant
 * @version 1.O
 */
public class Airport {
    private String oaciCode;
    private String name;
    private String country;
    private float latitude;
    private float longitude;

    /**
     * @param oaciCode the OACI code of the airport should be 4 letters
     * @param name the name of the airport
     * @param country the country of the airport
     * @param latitude the latitude of the airport
     * @param longitude the longitude of the airport
     */
    public Airport(String oaciCode, String name, String country, float latitude, float longitude) {
        this.oaciCode = oaciCode;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Airport(String oaci,Context context) {
        this.oaciCode=oaci;
        JSONObject airport = getAirportFromOACI(oaci,context);
        this.oaciCode=oaci;
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
            InputStream is = context.getAssets().open("names.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONArray aiportList = new JSONArray(json);
            for (int i = 0; i < aiportList.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject airport = aiportList.getJSONObject(i);
                if(airport.getString("ICAO").matches(oaci)){
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
