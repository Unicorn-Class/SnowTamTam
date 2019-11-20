package model;

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
        return ObservationDate;
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

    public Snowtam(String codedSnowtam) {

        String[] parsedSnowtam = codedSnowtam.split("\\)");
        this.placeAirport=new Airport(parsedSnowtam[1]);
        this.ObservationDate=parsedSnowtam[2];
        this.runway=new Runway(parsedSnowtam[3]);
    }



}