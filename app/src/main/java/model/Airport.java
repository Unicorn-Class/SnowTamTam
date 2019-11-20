package model;

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

    public Airport(String oaci) {
        this.oaciCode=oaci;
        getAirportFromOACI(oaci);
    }

    private void getAirportFromOACI(String oaci) {
        /*TODO parse oaci to get airport information*/
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
